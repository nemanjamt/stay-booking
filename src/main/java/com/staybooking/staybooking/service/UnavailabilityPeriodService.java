package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.unavailabilityPeriod.request.UnavailabilityPeriodCreate;
import com.staybooking.staybooking.dto.unavailabilityPeriod.response.UnavailabilityPeriodResponse;
import com.staybooking.staybooking.model.others.UnavailabilityPeriod;

import java.time.LocalDateTime;
import java.util.List;

public interface UnavailabilityPeriodService {

    boolean checkIfExistsUnavailabilityPeriodBetweenDatesByAccommodation(Long accommodationId, LocalDateTime startDate, LocalDateTime endDate);
    UnavailabilityPeriodResponse unavailabilityPeriodToResponse(UnavailabilityPeriod unavailabilityPeriod);
    APIResponse<UnavailabilityPeriodResponse> getUnavailabilityPeriodResponse(Long id);
    UnavailabilityPeriod findUnavailabilityPeriod(Long id);
    APIResponse<UnavailabilityPeriodResponse> createUnavailabilityPeriod(UnavailabilityPeriodCreate unavailabilityPeriodCreate);
    APIResponse<Boolean> deleteUnavailabilityPeriod(Long id);
    List<UnavailabilityPeriod> findUnavailabilityPeriodsByAccommodation(Long accommodationId) ;
    APIResponse<List<UnavailabilityPeriodResponse>> getUnavailabilityPeriodsByAccommodationResponse(Long accommodationId);
}
