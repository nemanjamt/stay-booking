package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
