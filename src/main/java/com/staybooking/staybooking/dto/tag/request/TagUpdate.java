package com.staybooking.staybooking.dto.tag.request;

import com.staybooking.staybooking.dto.image.request.ImageUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagUpdate {
    @NotNull
    @NotBlank
    String name;
    ImageUpdate image;
}
