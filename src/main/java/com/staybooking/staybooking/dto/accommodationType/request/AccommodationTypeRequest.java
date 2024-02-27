package com.staybooking.staybooking.dto.accommodationType.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccommodationTypeRequest {
    @NotNull
    @NotBlank
    private String name;
}
