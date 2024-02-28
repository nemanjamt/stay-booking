package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.image.request.ImageCreate;
import com.staybooking.staybooking.dto.image.request.ImageUpdate;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Image;
import com.staybooking.staybooking.repository.ImageRepository;
import com.staybooking.staybooking.service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;

    private ModelMapper modelMapper;
    public ImageServiceImpl(ImageRepository imageRepository, ModelMapper modelMapper){
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Image createImage(ImageCreate imageToCreate) {
        Image image = modelMapper.map(imageToCreate, Image.class);
        Image createdImage = imageRepository.save(image);
        return createdImage;
    }


    @Override
    public Image createImage(ImageCreate imageToCreate, Accommodation accommodation) {
        Image image = modelMapper.map(imageToCreate, Image.class);
        image.setAccommodation(accommodation);
        Image createdImage = imageRepository.save(image);
        return createdImage;
    }


    @Override
    public Image updateImage(Long id, ImageUpdate imageUpdate) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.IMAGE)));
        image.setPath(imageUpdate.getPath());
        Image updatedImage = imageRepository.save(image);
        return updatedImage;
    }
}
