package com.staybooking.staybooking.service;

import com.staybooking.staybooking.dto.price.request.PriceCreate;
import com.staybooking.staybooking.dto.price.response.PriceResponse;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.exceptions.OverlappingPriceDateRangeException;
import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Price;
import com.staybooking.staybooking.repository.PriceRepository;
import com.staybooking.staybooking.service.implementation.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceUnitTests {
    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AccommodationService accommodationService;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    public void testCreatePriceThrowsOverlappingPriceDateRangeException() {
        PriceCreate priceToCreate = new PriceCreate();
        priceToCreate.setAccommodationId(1L);
        priceToCreate.setStartDate(LocalDate.of(2024, 1, 1));
        priceToCreate.setEndDate(LocalDate.of(2024, 1, 10));
        priceToCreate.setPrice(1000.0);

        when(priceRepository.existsOverlappingPrice(priceToCreate.getAccommodationId(), priceToCreate.getStartDate(), priceToCreate.getEndDate()))
                .thenReturn(true);

        assertThrows(OverlappingPriceDateRangeException.class, () -> {
            priceService.createPrice(priceToCreate);
        });
    }

    @Test
    public void testCreatePriceSuccessfully() {
        PriceCreate priceToCreate = new PriceCreate();
        priceToCreate.setAccommodationId(1L);
        priceToCreate.setStartDate(LocalDate.of(2024, 1, 1));
        priceToCreate.setEndDate(LocalDate.of(2024, 1, 10));
        priceToCreate.setPrice(1000.0);

        Price price = new Price();
        price.setPrice(priceToCreate.getPrice());
        price.setStartDate(priceToCreate.getStartDate());
        price.setEndDate(priceToCreate.getEndDate());
        Accommodation accommodation = new Accommodation();
        accommodation.setId(priceToCreate.getAccommodationId());
        price.setAccommodation(accommodation);

        Price savedPrice = new Price();
        savedPrice.setId(1L);
        savedPrice.setPrice(priceToCreate.getPrice());
        savedPrice.setStartDate(priceToCreate.getStartDate());
        savedPrice.setEndDate(priceToCreate.getEndDate());
        savedPrice.setAccommodation(accommodation);

        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setId(1L);
        priceResponse.setPrice(priceToCreate.getPrice());
        priceResponse.setStartDate(priceToCreate.getStartDate());
        priceResponse.setEndDate(priceToCreate.getEndDate());
        priceResponse.setAccommodationId(priceToCreate.getAccommodationId());

        when(priceRepository.existsOverlappingPrice(priceToCreate.getAccommodationId(), priceToCreate.getStartDate(), priceToCreate.getEndDate()))
                .thenReturn(false);

        when(priceRepository.save(any(Price.class)))
                .thenReturn(savedPrice);
        when(modelMapper.map(savedPrice, PriceResponse.class))
                .thenReturn(priceResponse);

        when(accommodationService.findAccommodation(priceToCreate.getAccommodationId())).thenReturn(accommodation);

        APIResponse<PriceResponse> response = priceService.createPrice(priceToCreate);

        assertEquals(response.getHttpStatus(), 201);
        assertEquals(response.getInternalCode(), "2001");
        assertEquals(response.getData(), priceResponse);
        assertEquals(response.getMessage(), "Price successful created");
    }
}
