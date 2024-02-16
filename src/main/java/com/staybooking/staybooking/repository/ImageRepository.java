package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
