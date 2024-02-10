package com.boardcamp.api.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.errors.NotFoundException;
import com.boardcamp.api.errors.UnprocessableEntityException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.models.GameModel;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.repositories.CustomerRepository;
import com.boardcamp.api.repositories.GameRepository;
import com.boardcamp.api.repositories.RentalRepository;

@Service
public class RentalService {
  final CustomerRepository customerRepository;
  final GameRepository gameRepository;
  final RentalRepository rentalRepository;

  RentalService(CustomerRepository customerRepository, GameRepository gameRepository, RentalRepository rentalRepository) {
    this.customerRepository = customerRepository;
    this.gameRepository = gameRepository;
    this.rentalRepository = rentalRepository;
  }

  public RentalModel update(Long id) {
    RentalModel rental = rentalRepository.findById(id).orElseThrow(
      () -> new NotFoundException("Rental not found!")
    );
    if (rental.getReturnDate() != null){
      throw new UnprocessableEntityException("Game already returned!");
    }
    rental.setReturnDate(LocalDate.now());
    Integer daysToCalculateFee = (int) (Math.abs(ChronoUnit.DAYS.between(rental.getRentDate(), rental.getReturnDate())) - rental.getDaysRented());
    if (daysToCalculateFee > 0) {
      GameModel game = rental.getGame();
      rental.setDelayFee(game.getPricePerDay() * daysToCalculateFee);
    }
    return rentalRepository.save(rental);
  }

  public List<RentalModel> findAll() {
    return rentalRepository.findAll();
  }

  public Optional<RentalModel> save(RentalDTO dto) {
    
    CustomerModel customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(
      () -> new NotFoundException("Customer not found!")
    );

    GameModel game = gameRepository.findById(dto.getGameId()).orElseThrow(
      () -> new NotFoundException("Game not found!")
    );

    List<RentalModel> rentalList = rentalRepository.findCompletedRentalsByGameId(game.getId());

    if (rentalList.size() >= game.getStockTotal()) {
      throw new UnprocessableEntityException("There're no more games available!");
    }
    
    RentalModel rental = new RentalModel(dto, customer, game);
    return Optional.of(rentalRepository.save(rental));
  }
}
