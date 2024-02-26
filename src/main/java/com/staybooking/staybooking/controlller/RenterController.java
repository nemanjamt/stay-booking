package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.service.RenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/renter")
public class RenterController {
    private RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService){
        this.renterService = renterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> findRenter(@PathVariable Long id){
        APIResponse<UserInfo> apiResponse = renterService.findRenter(id);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<UserInfo>> createRenter(@Valid @RequestBody UserCreate renterToCreate){
        APIResponse<UserInfo> apiResponse = renterService.createRenter(renterToCreate);
        return new ResponseEntity(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> updateRenter(@PathVariable Long id, @Valid @RequestBody UserUpdate renterToUpdate){
        APIResponse<UserInfo> apiResponse = renterService.updateRenter(id, renterToUpdate);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<APIResponse<Boolean>> blockRenter(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = renterService.blockRenter(id);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/unblock/{id}")
    public ResponseEntity<APIResponse<Boolean>> unblockRenter(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = renterService.unblockRenter(id);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }


}
