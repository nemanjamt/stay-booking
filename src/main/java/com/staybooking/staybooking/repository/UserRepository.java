package com.staybooking.staybooking.repository;

import com.staybooking.staybooking.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     boolean existsByEmail(String email);
     boolean existsByPhoneNumber(String phoneNumber);
     boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
