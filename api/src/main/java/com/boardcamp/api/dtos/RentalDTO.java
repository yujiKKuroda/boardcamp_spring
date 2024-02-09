package com.boardcamp.api.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RentalDTO {
    @NotBlank
    private LocalDate rentDate;

    @Min(value = 1, message = "Days rented must be greater than 0")
    private Integer daysRented;

    private LocalDate returnDate = null;

    @Min(value = 1, message = "Price must be greater than 0")
    private Integer originalPrice;

    @NotNull
    private Integer delayFee = 0;
}
