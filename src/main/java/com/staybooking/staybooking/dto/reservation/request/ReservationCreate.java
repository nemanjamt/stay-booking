package com.staybooking.staybooking.dto.reservation.request;

import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.users.Renter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreate {
    @NotNull
    @NotBlank
    private LocalDate startDate;
    @NotNull
    @NotBlank
    private LocalDate endDate;
    @NotNull
    @NotBlank
    private Long renterId;
    @NotNull
    @NotBlank
    private Long accommodationId;
}
