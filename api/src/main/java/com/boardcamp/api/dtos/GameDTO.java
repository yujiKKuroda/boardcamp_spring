package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GameDTO {
  @NotBlank
  private String name;

  @NotBlank
  private String image;

  @NotBlank
  private Integer stockTotal;

  @NotBlank
  private Integer pricePerDay;
}
