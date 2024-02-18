package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.exceptions.EmailAlreadyUsedException;
import com.staybooking.staybooking.exceptions.PhoneNumberAlreadyUsedException;
import com.staybooking.staybooking.model.users.Renter;
import com.staybooking.staybooking.repository.RenterRepository;
import com.staybooking.staybooking.service.RenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RenterServiceImpl implements RenterService {
    private RenterRepository renterRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RenterServiceImpl(RenterRepository renterRepository, ModelMapper modelMapper){
        this.renterRepository = renterRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserInfo createRenter(UserCreate renterToCreate) {
        Renter renter = modelMapper.map(renterToCreate, Renter.class);
        if(renterRepository.existsByEmail(renterToCreate.getEmail())){
            throw new EmailAlreadyUsedException("Specified email is already used");
        }

        if(renterRepository.existsByPhoneNumber(renterToCreate.getPhoneNumber())){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }

        Renter createdRenter = renterRepository.save(renter);
        return modelMapper.map(createdRenter, UserInfo.class);
    }
}
