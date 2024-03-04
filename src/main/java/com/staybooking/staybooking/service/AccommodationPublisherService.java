package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.model.users.AccommodationPublisher;

public interface AccommodationPublisherService {
    APIResponse<UserInfo> createAccommodationPublisher(UserCreate accommodationPublisherToCreate);
    APIResponse<UserInfo> updateAccommodationPublisher(Long id, UserUpdate accommodationPublisherToUpdate);

    APIResponse<UserInfo> getAccommodationPublisherApiResponse(Long id);
    AccommodationPublisher findAccommodationPublisher(Long id);
    APIResponse<Boolean> blockAccommodationPublisher(Long id);
    APIResponse<Boolean> unblockAccommodationPublisher(Long id);
}
