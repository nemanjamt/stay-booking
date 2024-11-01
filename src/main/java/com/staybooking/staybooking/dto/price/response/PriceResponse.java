package com.staybooking.staybooking.dto.price.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {

    private Long id;

    private Long accommodationId;

    private LocalDate startDate;

    private LocalDate endDate;

    private double price;
}
