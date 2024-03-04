package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    List<Accommodation> findAccommodationByPublisherId(Long publisherId);
}
