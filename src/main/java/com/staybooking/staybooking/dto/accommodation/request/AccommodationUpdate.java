package com.staybooking.staybooking.dto.accommodation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationUpdate {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    private int bedNumber;
    @NotNull
    private int roomNumber;
    @NotNull
    private double defaultPrice;
    @NotNull
    private Long accommodationTypeId;
    @NotNull
    private List<Long> tagIds;
}
