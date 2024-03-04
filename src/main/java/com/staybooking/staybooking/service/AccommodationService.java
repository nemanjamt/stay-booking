package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.accommodation.request.AccommodationCreate;
import com.staybooking.staybooking.dto.accommodation.request.AccommodationUpdate;
import com.staybooking.staybooking.dto.accommodation.response.AccommodationResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.model.others.Accommodation;

import java.util.List;

public interface AccommodationService {
    APIResponse<AccommodationResponse> createAccommodation(AccommodationCreate accommodationToCreate);
    APIResponse<AccommodationResponse> updateAccommodation(Long id, AccommodationUpdate accommodationToUpdate);
    APIResponse<AccommodationResponse> getAccommodationApiResponse(Long id);
    Accommodation findAccommodation(Long id);
    APIResponse<List<AccommodationResponse>> findAccommodationsByPublisher(Long publisherId);
    APIResponse<Boolean> deleteAccommodation(Long id);
}
