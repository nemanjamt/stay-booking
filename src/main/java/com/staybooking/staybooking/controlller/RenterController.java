package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/renter")
public class RenterController {
    private RenterService renterService;

    @Autowired
    public RenterController(RenterService renterService){
        this.renterService=renterService;
    }

    @PostMapping
    public ResponseEntity<UserInfo> createRenter(@RequestBody UserCreate renterToCreate){
        UserInfo createdRenter = renterService.createRenter(renterToCreate);
        return new ResponseEntity(createdRenter, HttpStatus.CREATED);
    }

}
