package com.staybooking.staybooking.dto.accommodation.response;

import com.staybooking.staybooking.dto.accommodationType.response.AccommodationTypeResponse;
import com.staybooking.staybooking.dto.tag.response.TagResponse;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationResponse {
    private Long id;
    private String name;
    private String description;
    private int bedNumber;
    private int roomNumber;
    private double defaultPrice;
    private AccommodationTypeResponse accommodationType;
    private UserInfo accommodationPublisher;
    private List<TagResponse> tags;
}
