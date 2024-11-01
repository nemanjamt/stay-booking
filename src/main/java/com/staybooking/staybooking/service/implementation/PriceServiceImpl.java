package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.price.request.PriceCreate;
import com.staybooking.staybooking.dto.price.request.PriceUpdate;
import com.staybooking.staybooking.dto.price.response.PriceResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.exceptions.OverlappingPriceDateRangeException;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Price;
import com.staybooking.staybooking.repository.PriceRepository;
import com.staybooking.staybooking.service.AccommodationService;
import com.staybooking.staybooking.service.PriceService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final AccommodationService accommodationService;
    private final ModelMapper modelMapper;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository, AccommodationService accommodationService, ModelMapper modelMapper){
        this.priceRepository = priceRepository;
        this.accommodationService = accommodationService;
        this.modelMapper = modelMapper;
    }

    private PriceResponse mapPriceToResponse(Price price){
        PropertyMap<Price, PriceResponse> propertyMap = new PropertyMap<Price, PriceResponse>() {
            @Override
            protected void configure() {
                map(source.getAccommodation().getId(), destination.getAccommodationId());
            }
        };

        return modelMapper.map(price, PriceResponse.class);
    }

    @Override
    public APIResponse<PriceResponse> createPrice(PriceCreate priceToCreate) throws OverlappingPriceDateRangeException {
        if(priceRepository.existsOverlappingPrice(priceToCreate.getAccommodationId(), priceToCreate.getStartDate(), priceToCreate.getEndDate())) {
            throw new OverlappingPriceDateRangeException("The price range overlaps with an existing price for the accommodation");
        }
        Price createdPrice = createPriceFromDto(priceToCreate);
        PriceResponse priceResponse = mapPriceToResponse(priceRepository.save(createdPrice));
        return APIResponse.generateApiResponse(priceResponse, HttpStatus.CREATED, "2001","Price successful created");
    }

    @Override
    public APIResponse<PriceResponse> updatePrice(Long id, PriceUpdate priceToUpdate) {
        Price price = findPrice(id);
        price.setEndDate(priceToUpdate.getEndDate());
        price.setStartDate(priceToUpdate.getStartDate());
        price.setPrice(priceToUpdate.getPrice());
        PriceResponse priceResponse = mapPriceToResponse(priceRepository.save(price));
        return APIResponse.generateApiResponse(priceResponse, HttpStatus.OK, "2000","Price successful updated");
    }

    @Override
    public APIResponse<PriceResponse> getPriceApiResponse(Long id) {
        Price price = findPrice(id);
        PriceResponse priceResponse = mapPriceToResponse(price);
        return APIResponse.generateApiResponse(priceResponse, HttpStatus.OK, "2000","Price successful updated");
    }

    @Override
    public Price findPrice(Long id) {
        return priceRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.PRICE)));
    }

    @Override
    public APIResponse<List<PriceResponse>> findPricesByAccommodation(Long accommodationId) {
        List<PriceResponse> prices = priceRepository.findByAccommodationId(accommodationId, LocalDate.now()).stream().map(this::mapPriceToResponse).toList();
        return APIResponse.generateApiResponse(prices, HttpStatus.OK, "2000","Prices successful founded");
    }

    @Override
    public APIResponse<Boolean> deletePrice(Long id) {
        Price price = findPrice(id);
        price.setDeleted(true);
        priceRepository.save(price);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000","Price successful deleted");
    }

    @Override
    public Price createPriceFromDto(PriceCreate priceToCreate) {
        Accommodation accommodation = accommodationService.findAccommodation(priceToCreate.getAccommodationId());
        Price price = new Price();
        price.setPrice(priceToCreate.getPrice());
        price.setStartDate(priceToCreate.getStartDate());
        price.setEndDate(priceToCreate.getEndDate());
        price.setAccommodation(accommodation);
        return price;
    }


}
