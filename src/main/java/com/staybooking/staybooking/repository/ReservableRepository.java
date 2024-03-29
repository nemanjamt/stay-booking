package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.UnavailabilityPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservableRepository extends JpaRepository<UnavailabilityPeriod,Long> {
}
