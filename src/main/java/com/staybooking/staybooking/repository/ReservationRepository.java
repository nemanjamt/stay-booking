package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.accommodation.id = :accommodationId AND r.deleted = false AND " +
            "r.canceled = false AND " +
            "(:startDate BETWEEN r.startDate AND r.endDate OR " +
            ":endDate BETWEEN r.startDate AND r.endDate OR " +
            "r.startDate BETWEEN :startDate AND :endDate OR " +
            "r.endDate BETWEEN :startDate AND :endDate)")
    boolean existsOverlappingReservation(@Param("accommodationId") Long accommodationId,
                                   @Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

    List<Reservation> findReservationsByAccommodationId(Long accommodationId);
}
