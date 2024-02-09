package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalDTO;
import com.boardcamp.api.errors.BadRequestException;
import com.boardcamp.api.models.RentalModel;
import com.boardcamp.api.services.RentalService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rentals")
public class RentalController {

  final RentalService rentalService;

  public RentalController(RentalService rentalService) {
      this.rentalService = rentalService;
  }

  @PostMapping
  public ResponseEntity<Object> createCustomer(@Validated @RequestBody @Valid RentalDTO body, BindingResult result) {
      if (result.hasErrors()) {
          throw new BadRequestException("Invalid values!");
      }

      Optional<RentalModel> rental = rentalService.save(body);
      return ResponseEntity.status(HttpStatus.CREATED).body(rental);
  }
}
