package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GameDTO {
  @NotBlank
  private String name;

  @NotBlank
  private String image;

  @Min(value = 1, message = "Stock must be greater than 0")
  @NotNull(message= "Stock may not be empty")
  private Integer stockTotal;

  @Min(value = 1, message = "Price must be greater than 0")
  @NotNull(message= "Price may not be empty")
  private Integer pricePerDay;
}
