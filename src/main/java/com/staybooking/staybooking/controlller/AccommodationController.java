package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.accommodation.request.AccommodationCreate;
import com.staybooking.staybooking.dto.accommodation.request.AccommodationUpdate;
import com.staybooking.staybooking.dto.accommodation.response.AccommodationResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.service.AccommodationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
public class AccommodationController {
    private final AccommodationService accommodationService;
    @Autowired
    public AccommodationController(AccommodationService accommodationService){
        this.accommodationService = accommodationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AccommodationResponse>> findAccommodation(@PathVariable Long id){
        APIResponse<AccommodationResponse> apiResponse = accommodationService.getAccommodationApiResponse(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @GetMapping("/publisher/{publisherId}")
    public ResponseEntity<APIResponse<List<AccommodationResponse>>> findPublisherAccommodations(@PathVariable Long publisherId){
        APIResponse<List<AccommodationResponse>> apiResponse = accommodationService.findAccommodationsByPublisher(publisherId);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PostMapping
    public ResponseEntity<APIResponse<AccommodationResponse>> createAccommodation(@Valid @RequestBody AccommodationCreate accommodationToCreate){
        APIResponse<AccommodationResponse> apiResponse = accommodationService.createAccommodation(accommodationToCreate);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AccommodationResponse>> updateAccommodation(@PathVariable Long id, @Valid @RequestBody AccommodationUpdate accommodationToUpdate){
        APIResponse<AccommodationResponse> apiResponse = accommodationService.updateAccommodation(id, accommodationToUpdate);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteAccommodation(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = accommodationService.deleteAccommodation(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }






}
