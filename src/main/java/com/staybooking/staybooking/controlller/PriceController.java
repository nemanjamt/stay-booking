package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.accommodation.response.AccommodationResponse;
import com.staybooking.staybooking.dto.price.request.PriceCreate;
import com.staybooking.staybooking.dto.price.request.PriceUpdate;
import com.staybooking.staybooking.dto.price.response.PriceResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.service.PriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController {
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<PriceResponse>> findPrice(@PathVariable Long id) {
        APIResponse<PriceResponse> priceApiResponse = priceService.getPriceApiResponse(id);
        return new ResponseEntity<>(priceApiResponse, HttpStatus.valueOf(priceApiResponse.getHttpStatus()));
    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<APIResponse<List<PriceResponse>>> findAccommodationPrices(@PathVariable Long accommodationId) {
        APIResponse<List<PriceResponse>> accommodationPricesApiResponse = priceService.findPricesByAccommodation(accommodationId);
        return new ResponseEntity<>(accommodationPricesApiResponse, HttpStatus.valueOf(accommodationPricesApiResponse.getHttpStatus()));
    }

    @PostMapping
    public ResponseEntity<APIResponse<PriceResponse>> createResponse(@Valid PriceCreate priceCreate) {
        APIResponse<PriceResponse> createdPriceApiResponse = priceService.createPrice(priceCreate);
        return new ResponseEntity<>(createdPriceApiResponse, HttpStatus.valueOf(createdPriceApiResponse.getHttpStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<PriceResponse>> updatePrice(@PathVariable Long id, @Valid PriceUpdate priceToUpdate) {
        APIResponse<PriceResponse> updatedPriceApiResponse = priceService.updatePrice(id, priceToUpdate);
        return new ResponseEntity<>(updatedPriceApiResponse, HttpStatus.valueOf(updatedPriceApiResponse.getHttpStatus()));
    }

    @DeleteMapping("/id")
    public ResponseEntity<APIResponse<Boolean>> deletePrice(@PathVariable Long id) {
        APIResponse<Boolean> apiResponse = priceService.deletePrice(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

}
