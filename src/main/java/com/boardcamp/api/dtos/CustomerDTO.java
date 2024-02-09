package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerDTO {
  @NotBlank
  private String name;

  @NotBlank
  @Size(min = 11, max = 11, message = "CPF must have exactly 11 characters")
  private String cpf;
}
