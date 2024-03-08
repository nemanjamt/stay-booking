package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.accommodation.request.AccommodationCreate;
import com.staybooking.staybooking.dto.accommodation.request.AccommodationUpdate;
import com.staybooking.staybooking.dto.accommodation.response.AccommodationResponse;
import com.staybooking.staybooking.dto.accommodationType.response.AccommodationTypeResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.tag.response.TagResponse;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.AccommodationType;
import com.staybooking.staybooking.model.others.Tag;
import com.staybooking.staybooking.model.users.AccommodationPublisher;
import com.staybooking.staybooking.repository.AccommodationRepository;
import com.staybooking.staybooking.service.AccommodationPublisherService;
import com.staybooking.staybooking.service.AccommodationService;
import com.staybooking.staybooking.service.AccommodationTypeService;
import com.staybooking.staybooking.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private AccommodationRepository accommodationRepository;
    private AccommodationPublisherService accommodationPublisherService;
    private TagService tagService;
    private ModelMapper modelMapper;
    private AccommodationTypeService accommodationTypeService;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, ModelMapper modelMapper,
                                    AccommodationPublisherService accommodationPublisherService, TagService tagService,
                                    AccommodationTypeService accommodationTypeService){
        this.accommodationRepository = accommodationRepository;
        this.modelMapper = modelMapper;
        this.tagService = tagService;
        this.accommodationPublisherService = accommodationPublisherService;
        this.accommodationTypeService = accommodationTypeService;
        configureTypeMap();
    }

    private AccommodationResponse mapAccommodationToResponse(Accommodation accommodation){
        PropertyMap<Accommodation, AccommodationResponse> propertyMap = new PropertyMap<Accommodation, AccommodationResponse>() {
            @Override
            protected void configure() {
                map().setAccommodationType(modelMapper.map(source.getType(), AccommodationTypeResponse.class));
                map().setAccommodationPublisher(modelMapper.map(source.getPublisher(), UserInfo.class));
                map().setTags( source.getTags().stream().map(tag -> modelMapper.map(tag, TagResponse.class)).collect(Collectors.toList()));
            }
        };

        return modelMapper.map(accommodation, AccommodationResponse.class);
    }

    @Override
    public APIResponse<AccommodationResponse> createAccommodation(AccommodationCreate accommodationToCreate) {
        AccommodationPublisher accommodationPublisher = accommodationPublisherService.findAccommodationPublisher(accommodationToCreate.getPublisherId());
        AccommodationType accommodationType = accommodationTypeService.findAccommodationType(accommodationToCreate.getAccommodationTypeId());
        List<Tag> tags = accommodationToCreate.getTagIds().stream().map(tagService::findTag).collect(Collectors.toList());
        Accommodation accommodation = modelMapper.map(accommodationToCreate, Accommodation.class);
        accommodation.setPublisher(accommodationPublisher);
        accommodation.setType(accommodationType);
        accommodation.setTags(tags);
        AccommodationResponse accommodationResponse = mapAccommodationToResponse(accommodationRepository.save(accommodation));
        return APIResponse.generateApiResponse(accommodationResponse, HttpStatus.CREATED, "2001","Accommodation successful created");
    }



    @Override
    public APIResponse<AccommodationResponse> updateAccommodation(Long id, AccommodationUpdate accommodationToUpdate) {
        Accommodation accommodation = findAccommodation(id);
        accommodation.setName(accommodationToUpdate.getName());
        accommodation.setDescription(accommodationToUpdate.getDescription());
        accommodation.setTags(accommodationToUpdate.getTagIds().stream().map(tagService::findTag).collect(Collectors.toList()));
        accommodation.setType(accommodationTypeService.findAccommodationType(accommodationToUpdate.getAccommodationTypeId()));
        accommodation.setBedNumber(accommodationToUpdate.getBedNumber());
        accommodation.setRoomNumber(accommodationToUpdate.getRoomNumber());
        AccommodationResponse accommodationResponse = mapAccommodationToResponse(accommodationRepository.save(accommodation));
        return APIResponse.generateApiResponse(accommodationResponse, HttpStatus.OK, "2000","Accommodation successful updated");
    }


    @Override
    public APIResponse<AccommodationResponse> getAccommodationApiResponse(Long id) {
        AccommodationResponse accommodationResponse = mapAccommodationToResponse(findAccommodation(id));
        return APIResponse.generateApiResponse(accommodationResponse, HttpStatus.OK, "2000","Accommodation successful updated");
    }


    @Override
    public Accommodation findAccommodation(Long id) {
        return  accommodationRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.ACCOMMODATION)));
    }


    @Override
    public APIResponse<List<AccommodationResponse>> findAccommodationsByPublisher(Long publisherId) {
        List<AccommodationResponse> accommodationsResponse = accommodationRepository.findAccommodationByPublisherId(publisherId).stream().map(a -> mapAccommodationToResponse(a)).collect(Collectors.toList());
        return APIResponse.generateApiResponse(accommodationsResponse, HttpStatus.OK, "2000","Accommodations successful founded");
    }


    @Override
    public APIResponse<Boolean> deleteAccommodation(Long id) {
        Accommodation accommodation = findAccommodation(id);
        accommodation.setDeleted(true);
        accommodationRepository.save(accommodation);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000","Accommodation successful deleted");
    }
    private void configureTypeMap(){
        TypeMap<AccommodationCreate, Accommodation> propertyMapper = this.modelMapper.typeMap(AccommodationCreate.class, Accommodation.class);
        propertyMapper.addMappings(mapper -> mapper.skip(Accommodation::setId));
    }
}
