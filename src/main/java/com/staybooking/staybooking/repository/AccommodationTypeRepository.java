package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationTypeRepository extends JpaRepository<AccommodationType,Long> {
}
