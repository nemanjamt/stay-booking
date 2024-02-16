package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
}
