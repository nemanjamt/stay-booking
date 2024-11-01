package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.accommodation.id = :accommodationId AND p.endDate >= :currentDate AND p.deleted = false")
    List<Price> findByAccommodationId(Long accommodationId, LocalDate currentDate);

    @Query("SELECT COUNT(p) > 0 FROM Price p WHERE p.accommodation.id = :accommodationId AND p.deleted = false AND " +
            "(:startDate BETWEEN p.startDate AND p.endDate OR " +
            ":endDate BETWEEN p.startDate AND p.endDate OR " +
            "p.startDate BETWEEN :startDate AND :endDate OR " +
            "p.endDate BETWEEN :startDate AND :endDate)")
    boolean existsOverlappingPrice(@Param("accommodationId") Long accommodationId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);
}
