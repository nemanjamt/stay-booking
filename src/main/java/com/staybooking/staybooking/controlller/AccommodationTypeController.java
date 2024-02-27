package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.accommodationType.request.AccommodationTypeRequest;
import com.staybooking.staybooking.dto.accommodationType.response.AccommodationTypeResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.service.AccommodationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodationType")
public class AccommodationTypeController {
    private AccommodationTypeService accommodationTypeService;
    @Autowired
    public AccommodationTypeController(AccommodationTypeService accommodationTypeService){
        this.accommodationTypeService = accommodationTypeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AccommodationTypeResponse>> findAccommodationType(@PathVariable  Long id){
        APIResponse<AccommodationTypeResponse> apiResponse = accommodationTypeService.findAccommodationType(id);
        return new ResponseEntity(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PostMapping
    public ResponseEntity<APIResponse<AccommodationTypeResponse>> createAccommodationType(@RequestBody AccommodationTypeRequest accommodationTypeRequest){
        APIResponse<AccommodationTypeResponse> apiResponse = accommodationTypeService.createAccommodationType(accommodationTypeRequest);
        return new ResponseEntity(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<AccommodationTypeResponse>> changeAccommodationType(@PathVariable Long id, @RequestBody AccommodationTypeRequest accommodationTypeRequest){
        APIResponse<AccommodationTypeResponse> apiResponse = accommodationTypeService.updateAccommodationType(id, accommodationTypeRequest);
        return new ResponseEntity(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> deleteAccommodationType(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = accommodationTypeService.deleteAccommodationType(id);
        return new ResponseEntity(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

}
