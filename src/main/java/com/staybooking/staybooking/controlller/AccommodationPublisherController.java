package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.service.AccommodationPublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accommodationPublisher")
public class AccommodationPublisherController {
    private final AccommodationPublisherService accommodationPublisherService;
    @Autowired
    public AccommodationPublisherController(AccommodationPublisherService accommodationPublisherService){
        this.accommodationPublisherService = accommodationPublisherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> findAccommodationPublisher(@PathVariable Long id){
        APIResponse<UserInfo> apiResponse = accommodationPublisherService.getAccommodationPublisherApiResponse(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<UserInfo>> createAccommodationPublisher(@Valid @RequestBody UserCreate renterToCreate){
        APIResponse<UserInfo> apiResponse = accommodationPublisherService.createAccommodationPublisher(renterToCreate);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> updateAccommodationPublisher(@PathVariable Long id, @Valid @RequestBody UserUpdate renterToUpdate){
        APIResponse<UserInfo> apiResponse = accommodationPublisherService.updateAccommodationPublisher(id, renterToUpdate);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<APIResponse<Boolean>> blockAccommodationPublisher(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = accommodationPublisherService.blockAccommodationPublisher(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/unblock/{id}")
    public ResponseEntity<APIResponse<Boolean>> unblockAccommodationPublisher(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = accommodationPublisherService.unblockAccommodationPublisher(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
