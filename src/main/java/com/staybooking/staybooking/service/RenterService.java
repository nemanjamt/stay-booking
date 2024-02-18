package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.response.UserInfo;

public interface RenterService {
    UserInfo createRenter(UserCreate renterToCreate);
}
