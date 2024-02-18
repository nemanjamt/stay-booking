package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.users.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter,Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String email);
}
