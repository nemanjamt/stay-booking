package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.others.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
}
