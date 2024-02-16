package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}
