package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;

public interface RenterService {
    APIResponse<UserInfo> createRenter(UserCreate renterToCreate);
    APIResponse<UserInfo> updateRenter(Long id, UserUpdate renterToUpdate);
    APIResponse<Boolean> blockRenter(Long id);
    APIResponse<Boolean> unblockRenter(Long id);

    APIResponse<UserInfo> findRenter(Long id);
}
