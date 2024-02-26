package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;

public interface ModeratorService {
    APIResponse<UserInfo> createModerator(UserCreate moderatorToCreate);
    APIResponse<UserInfo> updateModerator(Long id, UserUpdate moderatorToUpdate);
    APIResponse<Boolean> deleteModerator(Long id);
    APIResponse<UserInfo> findModerator(Long id);
}
