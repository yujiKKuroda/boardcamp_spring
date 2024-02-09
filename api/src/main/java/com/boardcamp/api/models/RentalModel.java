package com.boardcamp.api.models;

import java.time.LocalDate;

import com.boardcamp.api.dtos.RentalDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class RentalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameModel game;

    private LocalDate rentDate;

    private Integer daysRented;

    private LocalDate returnDate;

    private Integer originalPrice;

    private Integer delayFee;

    public RentalModel(RentalDTO dto, CustomerModel customer, GameModel game) {
        this.customer = new CustomerModel(customer.getId(), customer.getName(), customer.getCpf());
        this.game = new GameModel(game.getId(), game.getName(), game.getImage(), game.getStockTotal(), game.getPricePerDay());
        this.rentDate = dto.getRentDate();
        this.daysRented = dto.getDaysRented();
        this.returnDate = dto.getReturnDate();
        this.originalPrice = dto.getOriginalPrice();
        this.delayFee = dto.getDelayFee();
    }
}
