package com.boardcamp.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.errors.BadRequestException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.services.CustomerService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customers")
public class CustomerController {

  final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
      this.customerService = customerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id) {
      CustomerModel customer = customerService.findById(id);
      return ResponseEntity.status(HttpStatus.OK).body(customer);
  }

  @PostMapping
  public ResponseEntity<Object> createCustomer(@Validated @RequestBody @Valid CustomerDTO body, BindingResult result) {
      if (result.hasErrors()) {
          throw new BadRequestException("Invalid values!");
      }

      Optional<CustomerModel> customer = customerService.save(body);
      return ResponseEntity.status(HttpStatus.CREATED).body(customer);
  }
}
