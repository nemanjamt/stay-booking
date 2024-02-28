package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.image.request.ImageCreate;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.tag.request.TagCreate;
import com.staybooking.staybooking.dto.tag.request.TagUpdate;
import com.staybooking.staybooking.dto.tag.response.TagResponse;
import com.staybooking.staybooking.model.others.Image;
import com.staybooking.staybooking.model.others.Tag;
import com.staybooking.staybooking.repository.TagRepository;
import com.staybooking.staybooking.service.ImageService;
import com.staybooking.staybooking.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;
    private ModelMapper modelMapper;

    private ImageService imageService;
    @Autowired
    public TagServiceImpl(TagRepository tagRepository, ModelMapper modelMapper, ImageService imageService){
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
        this.imageService = imageService;
    }

    @Override
    public APIResponse<TagResponse> findTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.TAG)));
        TagResponse tagResponse = modelMapper.map(tag, TagResponse.class);
        return APIResponse.generateApiResponse(tagResponse, HttpStatus.OK, "2000", "Tag successful found");
    }

    @Override
    public APIResponse<TagResponse> createTag(TagCreate tagToCreate) {
        Tag tag = modelMapper.map(tagToCreate, Tag.class);
        if(tagToCreate.getImage() != null){
            Image createdImage = imageService.createImage(tagToCreate.getImage());
            tag.setImage(createdImage);
        }
        TagResponse tagResponse = modelMapper.map(tagRepository.save(tag), TagResponse.class);
        return APIResponse.generateApiResponse(tagResponse, HttpStatus.OK, "2001", "Tag successful created");
    }

    @Override
    public APIResponse<TagResponse> updateTag(Long id, TagUpdate tagToUpdate) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.TAG)));
        tag.setName(tagToUpdate.getName());
        if(tagToUpdate.getImage() != null){
            if(tag.getImage() != null){
                //update existing image
                imageService.updateImage(tag.getImage().getId(), tagToUpdate.getImage());
            }else{
                //create new image
                Image createdImage = imageService.createImage(modelMapper.map(tagToUpdate.getImage(), ImageCreate.class));
                tag.setImage(createdImage);
            }
        }
        TagResponse tagResponse = modelMapper.map(tagRepository.save(tag), TagResponse.class);
        return APIResponse.generateApiResponse(tagResponse, HttpStatus.OK, "2000", "Tag successful updated");
    }

    @Override
    public APIResponse<Boolean> deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.TAG)));
        tag.setDeleted(true);
        tagRepository.save(tag);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Tag successful deleted");
    }
}
