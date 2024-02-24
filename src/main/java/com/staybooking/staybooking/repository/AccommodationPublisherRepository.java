package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.users.AccommodationPublisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationPublisherRepository extends JpaRepository<AccommodationPublisher, Long> {
}
