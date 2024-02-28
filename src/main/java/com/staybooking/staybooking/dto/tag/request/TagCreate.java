package com.staybooking.staybooking.dto.tag.request;

import com.staybooking.staybooking.dto.image.request.ImageCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagCreate {
    @NotNull
    @NotBlank
    private String name;
    private ImageCreate image;
}
