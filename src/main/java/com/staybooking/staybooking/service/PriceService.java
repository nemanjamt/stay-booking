package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.price.request.PriceCreate;
import com.staybooking.staybooking.dto.price.request.PriceUpdate;
import com.staybooking.staybooking.dto.price.response.PriceResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.exceptions.OverlappingPriceDateRangeException;
import com.staybooking.staybooking.model.others.Price;

import java.util.List;

public interface PriceService {
    APIResponse<PriceResponse> createPrice(PriceCreate priceToCreate) throws OverlappingPriceDateRangeException;
    APIResponse<PriceResponse> updatePrice(Long id, PriceUpdate priceToUpdate);
    APIResponse<PriceResponse> getPriceApiResponse(Long id);
    Price findPrice(Long id);
    APIResponse<List<PriceResponse>> findPricesByAccommodation(Long accommodationId);
    APIResponse<Boolean> deletePrice(Long id);
    Price createPriceFromDto(PriceCreate priceToCreate);
}
