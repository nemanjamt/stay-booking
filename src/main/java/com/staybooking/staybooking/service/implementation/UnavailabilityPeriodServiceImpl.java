package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.unavailabilityPeriod.request.UnavailabilityPeriodCreate;
import com.staybooking.staybooking.dto.unavailabilityPeriod.response.UnavailabilityPeriodResponse;
import com.staybooking.staybooking.exceptions.UnavailabilityPeriodAlreadyDefined;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.UnavailabilityPeriod;
import com.staybooking.staybooking.repository.UnavailabilityPeriodRepository;
import com.staybooking.staybooking.service.AccommodationService;
import com.staybooking.staybooking.service.UnavailabilityPeriodService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnavailabilityPeriodServiceImpl implements UnavailabilityPeriodService {

    private UnavailabilityPeriodRepository unavailabilityPeriodRepository;
    private ModelMapper modelMapper;
    private AccommodationService accommodationService;
    @Autowired
    public UnavailabilityPeriodServiceImpl(UnavailabilityPeriodRepository unavailabilityPeriodRepository, ModelMapper modelMapper, AccommodationService accommodationService){
        this.unavailabilityPeriodRepository = unavailabilityPeriodRepository;
        this.modelMapper = modelMapper;
        this.accommodationService = accommodationService;
        configureTypeMap();
    }



    @Override
    public boolean checkIfExistsUnavailabilityPeriodBetweenDatesByAccommodation(Long accommodationId, LocalDateTime startDate, LocalDateTime endDate) {
        return unavailabilityPeriodRepository.checkIfExistsBetweenDatesByAccommodation(accommodationId, startDate, endDate);
    }

    @Override
    public UnavailabilityPeriodResponse unavailabilityPeriodToResponse(UnavailabilityPeriod unavailabilityPeriod) {
        TypeMap<UnavailabilityPeriod, UnavailabilityPeriodResponse> propertyMapper = this.modelMapper.typeMap(UnavailabilityPeriod.class, UnavailabilityPeriodResponse.class);
        Converter<Accommodation, Long> accommodationIdConverter = c -> c.getSource().getId();
        propertyMapper.addMappings(
                mapper -> mapper.using(accommodationIdConverter).map(UnavailabilityPeriod::getAccommodation, UnavailabilityPeriodResponse::setAccommodationId)
        );
        return modelMapper.map(unavailabilityPeriod, UnavailabilityPeriodResponse.class);
    }

    @Override
    public APIResponse<UnavailabilityPeriodResponse> getUnavailabilityPeriodResponse(Long id) {
        UnavailabilityPeriod unavailabilityPeriod = findUnavailabilityPeriod(id);
        UnavailabilityPeriodResponse unavailabilityPeriodResponse = unavailabilityPeriodToResponse(unavailabilityPeriod);
        return APIResponse.generateApiResponse(unavailabilityPeriodResponse, HttpStatus.OK, "2000","Unavailability period successful found");
    }



    @Override
    public UnavailabilityPeriod findUnavailabilityPeriod(Long id) {
        return unavailabilityPeriodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.UNAVAILABILITY_PERIOD)));
    }


    @Override
    public APIResponse<UnavailabilityPeriodResponse> createUnavailabilityPeriod(UnavailabilityPeriodCreate unavailabilityPeriodCreate) {
        if(checkIfExistsUnavailabilityPeriodBetweenDatesByAccommodation(unavailabilityPeriodCreate.getAccommodationId(), unavailabilityPeriodCreate.getStartDate(), unavailabilityPeriodCreate.getEndDate())){
            throw new UnavailabilityPeriodAlreadyDefined("Unavailable period already defined for chosen dates");
        }
        Accommodation accommodation = accommodationService.findAccommodation(unavailabilityPeriodCreate.getAccommodationId());

        UnavailabilityPeriod unavailabilityPeriod = modelMapper.map(unavailabilityPeriodCreate, UnavailabilityPeriod.class);
        unavailabilityPeriod.setAccommodation(accommodation);
        UnavailabilityPeriodResponse createdUnavailabilityPeriodResponse =  modelMapper.map(unavailabilityPeriodRepository.save(unavailabilityPeriod), UnavailabilityPeriodResponse.class);
        return APIResponse.generateApiResponse(createdUnavailabilityPeriodResponse, HttpStatus.CREATED, "2000","Unavailability period successful created");
    }


    @Override
    public APIResponse<Boolean> deleteUnavailabilityPeriod(Long id) {
        UnavailabilityPeriod unavailabilityPeriod = findUnavailabilityPeriod(id);
        unavailabilityPeriod.setDeleted(true);
        unavailabilityPeriodRepository.save(unavailabilityPeriod);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000","Unavailability period successful deleted");
    }


    @Override
    public List<UnavailabilityPeriod> findUnavailabilityPeriodsByAccommodation(Long accommodationId) {
        return  unavailabilityPeriodRepository.findUnavailabilityPeriodByAccommodationId(accommodationId);
    }

    @Override
    public APIResponse<List<UnavailabilityPeriodResponse>> getUnavailabilityPeriodsByAccommodationResponse(Long accommodationId) {
        List<UnavailabilityPeriod> unavailabilityPeriods = findUnavailabilityPeriodsByAccommodation(accommodationId);
        List<UnavailabilityPeriodResponse> unavailabilityPeriodsResponse = unavailabilityPeriods.stream().map(unavailabilityPeriod -> unavailabilityPeriodToResponse(unavailabilityPeriod)).collect(Collectors.toList());
        return APIResponse.generateApiResponse(unavailabilityPeriodsResponse, HttpStatus.OK, "2000","Unavailability periods by accommodation successful found");
    }

    private void configureTypeMap(){
        TypeMap<UnavailabilityPeriodCreate, UnavailabilityPeriod> propertyMapper = this.modelMapper.typeMap(UnavailabilityPeriodCreate.class, UnavailabilityPeriod.class);
        propertyMapper.addMappings(mapper -> mapper.skip(UnavailabilityPeriod::setId));
    }
}
