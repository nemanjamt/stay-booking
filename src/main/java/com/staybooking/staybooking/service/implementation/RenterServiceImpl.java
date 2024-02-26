package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.constants.EntityNames;
import com.staybooking.staybooking.constants.ErrorConstants;
import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.exceptions.EmailAlreadyUsedException;
import com.staybooking.staybooking.exceptions.PhoneNumberAlreadyUsedException;
import com.staybooking.staybooking.model.users.Renter;
import com.staybooking.staybooking.repository.RenterRepository;
import com.staybooking.staybooking.repository.UserRepository;
import com.staybooking.staybooking.service.RenterService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RenterServiceImpl implements RenterService {
    private RenterRepository renterRepository;
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Autowired
    public RenterServiceImpl(RenterRepository renterRepository, ModelMapper modelMapper, UserRepository userRepository){
        this.renterRepository = renterRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    @Override
    public APIResponse<UserInfo> createRenter(UserCreate renterToCreate) {
        Renter renter = modelMapper.map(renterToCreate, Renter.class);
        if(userRepository.existsByEmail(renterToCreate.getEmail())){
            throw new EmailAlreadyUsedException("Specified email is already used");
        }

        if(userRepository.existsByPhoneNumber(renterToCreate.getPhoneNumber())){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }

        Renter createdRenter = renterRepository.save(renter);
        UserInfo userInfo = modelMapper.map(createdRenter, UserInfo.class);
        return APIResponse.generateApiResponse(userInfo, HttpStatus.CREATED, "2001", "Renter successful created");
    }

    @Override
    public APIResponse<UserInfo> updateRenter(Long id, UserUpdate renterToUpdate) {
        if(userRepository.existsByPhoneNumberAndIdNot(renterToUpdate.getPhoneNumber(), id)){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }
        Renter renter = renterRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.RENTER)));
        renter.setFirstName(renterToUpdate.getFirstName());
        renter.setLastName(renterToUpdate.getLastName());
        renter.setPhoneNumber(renterToUpdate.getPhoneNumber());
        UserInfo updatedRenter = modelMapper.map(renterRepository.save(renter), UserInfo.class);


        return APIResponse.generateApiResponse(updatedRenter, HttpStatus.OK, "2000", "Renter successful updated");
    }

    @Override
    public APIResponse<Boolean> blockRenter(Long id) {
        Renter renter = renterRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.RENTER)));
        renter.setBlocked(true);
        renterRepository.save(renter);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Renter successful blocked");
    }

    @Override
    public APIResponse<Boolean> unblockRenter(Long id) {
        Renter renter = renterRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.RENTER)));
        renter.setBlocked(false);
        renterRepository.save(renter);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Renter successful unblocked");
    }


    @Override
    public APIResponse<UserInfo> findRenter(Long id) {
        Renter renter = renterRepository.findById(id).orElseThrow( () -> new EntityNotFoundException(String.format(ErrorConstants.ENTITY_WITH_ID_NOT_FOUND, EntityNames.RENTER)));
        UserInfo renterInfo = modelMapper.map(renter, UserInfo.class);
        return APIResponse.generateApiResponse(renterInfo, HttpStatus.OK, "2000", "Renter successful found");
    }


}
