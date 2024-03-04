package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.accommodationType.request.AccommodationTypeRequest;
import com.staybooking.staybooking.dto.accommodationType.response.AccommodationTypeResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.model.others.AccommodationType;

public interface AccommodationTypeService {
    APIResponse<AccommodationTypeResponse> getApiAccommodationTypeApiResponse(Long id);

    AccommodationType findAccommodationType(Long id);
    APIResponse<AccommodationTypeResponse> createAccommodationType(AccommodationTypeRequest accommodationTypeToCreate);
    APIResponse<AccommodationTypeResponse> updateAccommodationType(Long id, AccommodationTypeRequest accommodationTypeToUpdate);
    APIResponse<Boolean> deleteAccommodationType(Long id);

}
