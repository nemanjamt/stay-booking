package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.users.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
}
