package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.UnavailabilityPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UnavailabilityPeriodRepository extends JpaRepository<UnavailabilityPeriod, Long> {
    List<UnavailabilityPeriod> findUnavailabilityPeriodByAccommodationId(Long id);
    @Query("SELECT COUNT(u) > 0 FROM UnavailabilityPeriod u WHERE (NOT u.deleted) AND u.accommodation.id = :accommodationId AND NOT (u.startDate >= :checkEndDate OR u.endDate <= :checkStartDate)")
    boolean checkIfExistsBetweenDatesByAccommodation(Long accommodationId, LocalDateTime checkStartDate, LocalDateTime checkEndDate);
}
