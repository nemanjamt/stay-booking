package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
}
