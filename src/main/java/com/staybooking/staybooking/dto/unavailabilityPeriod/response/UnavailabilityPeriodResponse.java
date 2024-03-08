package com.staybooking.staybooking.dto.unavailabilityPeriod.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnavailabilityPeriodResponse {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long accommodationId;
}
