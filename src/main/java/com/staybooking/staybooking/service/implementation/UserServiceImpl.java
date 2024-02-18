package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.repository.UserRepository;
import com.staybooking.staybooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
