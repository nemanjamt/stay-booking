package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.unavailabilityPeriod.request.UnavailabilityPeriodCreate;
import com.staybooking.staybooking.dto.unavailabilityPeriod.response.UnavailabilityPeriodResponse;
import com.staybooking.staybooking.service.UnavailabilityPeriodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unavailabilityPeriod")
public class UnavailabilityPeriodController {
    private final UnavailabilityPeriodService unavailabilityPeriodService;
    @Autowired
    public UnavailabilityPeriodController(UnavailabilityPeriodService unavailabilityPeriodService){
        this.unavailabilityPeriodService = unavailabilityPeriodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<UnavailabilityPeriodResponse>> findUnavailabilityPeriod(@PathVariable Long id){
        APIResponse<UnavailabilityPeriodResponse> apiResponse = unavailabilityPeriodService.getUnavailabilityPeriodResponse(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @GetMapping("/accommodation/{accommodationId}")
    public ResponseEntity<APIResponse<List<UnavailabilityPeriodResponse>>> findUnavailabilityPeriodsByAccommodation(@PathVariable Long accommodationId){
        APIResponse<List<UnavailabilityPeriodResponse>> apiResponse = unavailabilityPeriodService.getUnavailabilityPeriodsByAccommodationResponse(accommodationId);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PostMapping
    public ResponseEntity<APIResponse<UnavailabilityPeriodResponse>> createUnavailabilityPeriod(@Valid  @RequestBody UnavailabilityPeriodCreate unavailabilityPeriodCreate){
        APIResponse<UnavailabilityPeriodResponse> apiResponse = unavailabilityPeriodService.createUnavailabilityPeriod(unavailabilityPeriodCreate);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteUnavailabilityPeriod(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = unavailabilityPeriodService.deleteUnavailabilityPeriod(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }


}
