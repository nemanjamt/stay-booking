package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.image.request.ImageCreate;
import com.staybooking.staybooking.dto.image.request.ImageUpdate;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Image;

public interface ImageService {
    Image createImage(ImageCreate imageToCreate);
    Image createImage(ImageCreate imageToCreate, Accommodation accommodation);

    Image updateImage(Long id, ImageUpdate imageUpdate);

}
