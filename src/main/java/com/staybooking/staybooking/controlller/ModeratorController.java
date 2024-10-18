package com.staybooking.staybooking.controlller;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.service.ModeratorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorController {
    private final ModeratorService moderatorService;

    public ModeratorController(ModeratorService moderatorService){
        this.moderatorService = moderatorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> findModerator(@PathVariable Long id){
        APIResponse<UserInfo> apiResponse = moderatorService.findModerator(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<UserInfo>> createModerator(@Valid @RequestBody UserCreate moderatorToCreate){
        APIResponse<UserInfo> apiResponse = moderatorService.createModerator(moderatorToCreate);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<UserInfo>> updateModerator(@PathVariable Long id, @Valid @RequestBody UserUpdate moderatorToUpdate){
        APIResponse<UserInfo> apiResponse = moderatorService.updateModerator(id, moderatorToUpdate);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Boolean>> blockModerator(@PathVariable Long id){
        APIResponse<Boolean> apiResponse = moderatorService.deleteModerator(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
