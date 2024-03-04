package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.accommodationType.request.AccommodationTypeRequest;
import com.staybooking.staybooking.dto.accommodationType.response.AccommodationTypeResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.model.others.AccommodationType;
import com.staybooking.staybooking.repository.AccommodationTypeRepository;
import com.staybooking.staybooking.service.AccommodationTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccommodationTypeServiceImpl implements AccommodationTypeService {
    private AccommodationTypeRepository accommodationTypeRepository;
    private ModelMapper modelMapper;
    @Autowired
    public AccommodationTypeServiceImpl(AccommodationTypeRepository accommodationTypeRepository, ModelMapper modelMapper){
        this.accommodationTypeRepository = accommodationTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public APIResponse<AccommodationTypeResponse> getApiAccommodationTypeApiResponse(Long id) {
        AccommodationType accommodationType =  findAccommodationType(id);
        AccommodationTypeResponse accommodationTypeResponse = modelMapper.map(accommodationType, AccommodationTypeResponse.class);
        return  APIResponse.generateApiResponse(accommodationTypeResponse, HttpStatus.OK, "2000","Accommodation type successful found");
    }

    @Override
    public AccommodationType findAccommodationType(Long id) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.ACCOMMODATION_TYPE)));
        return accommodationType;
    }

    @Override
    public APIResponse<AccommodationTypeResponse> createAccommodationType(AccommodationTypeRequest accommodationTypeToCreate) {
        AccommodationType accommodationType = modelMapper.map(accommodationTypeToCreate, AccommodationType.class);
        AccommodationTypeResponse response = modelMapper.map(accommodationTypeRepository.save(accommodationType), AccommodationTypeResponse.class);
        return APIResponse.generateApiResponse(response, HttpStatus.CREATED, "2001","Accommodation type successful created");
    }

    @Override
    public APIResponse<AccommodationTypeResponse> updateAccommodationType(Long id, AccommodationTypeRequest accommodationTypeToUpdate) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.ACCOMMODATION_TYPE)));
        accommodationType.setName(accommodationTypeToUpdate.getName());
        AccommodationTypeResponse response = modelMapper.map(accommodationTypeRepository.save(accommodationType), AccommodationTypeResponse.class);
        return APIResponse.generateApiResponse(response, HttpStatus.OK, "2000","Accommodation type successful updated");

    }

    @Override
    public APIResponse<Boolean> deleteAccommodationType(Long id) {
        AccommodationType accommodationType = accommodationTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.ACCOMMODATION_TYPE)));
        accommodationType.setDeleted(true);
        accommodationTypeRepository.save(accommodationType);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000","Accommodation type successful deleted");
    }
}
