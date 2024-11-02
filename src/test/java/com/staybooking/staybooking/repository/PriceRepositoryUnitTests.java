package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Accommodation;
import com.staybooking.staybooking.model.others.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
public class PriceRepositoryUnitTests {

    private final long FIRST_ACCOMMODATION_ID = 1L;
    @Autowired
    private PriceRepository priceRepository;


    @Test
    void testFindPrice(){
        Price price = priceRepository.findById(FIRST_ACCOMMODATION_ID).orElse(null);
        assertNotNull(price);
        assertEquals(1000, price.getPrice());
    }

    @Test
    void findPricesByAccommodationId(){
        LocalDate date = LocalDate.of(2024, 5, 23);
        List<Price> priceList = priceRepository.findByAccommodationId(FIRST_ACCOMMODATION_ID, date);
        assertEquals(4, priceList.size());
    }

    @Test
    public void testStartDateWithinExistingRange() {
        Long accommodationId = 1L;
        LocalDate startDate = LocalDate.of(2024, 1, 15);
        LocalDate endDate = LocalDate.of(2024, 1, 30);

        boolean exists = priceRepository.existsOverlappingPrice(accommodationId, startDate, endDate);

        assertTrue(exists);
    }

    @Test
    public void testEndDateWithinExistingRange() {
        Long accommodationId = 1L;
        LocalDate startDate = LocalDate.of(2023, 12, 15);
        LocalDate endDate = LocalDate.of(2024, 1, 15);

        boolean exists = priceRepository.existsOverlappingPrice(accommodationId, startDate, endDate);

        assertTrue(exists);
    }

    @Test
    public void testExistingStartDateWithinNewRange() {
        Long accommodationId = 1L;
        LocalDate startDate = LocalDate.of(2023, 12, 15);
        LocalDate endDate = LocalDate.of(2024, 3, 1);

        boolean exists = priceRepository.existsOverlappingPrice(accommodationId, startDate, endDate);

        assertTrue(exists);
    }

    @Test
    public void testExistingEndDateWithinNewRange() {
        Long accommodationId = 1L;
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 2, 15);

        boolean exists = priceRepository.existsOverlappingPrice(accommodationId, startDate, endDate);

        assertTrue(exists);
    }

    @Test
    public void testNoOverlap() {
        Long accommodationId = 1L;
        LocalDate startDate = LocalDate.of(2024, 2, 3);
        LocalDate endDate = LocalDate.of(2024, 4, 12);

        boolean exists = priceRepository.existsOverlappingPrice(accommodationId, startDate, endDate);

        assertFalse(exists);
    }
}
