package com.staybooking.staybooking.dto.tag.response;

import com.staybooking.staybooking.dto.image.response.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private Long id;
    private String name;
    private ImageResponse image;
}
