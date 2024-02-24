package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.exceptions.EmailAlreadyUsedException;
import com.staybooking.staybooking.exceptions.PhoneNumberAlreadyUsedException;
import com.staybooking.staybooking.model.users.AccommodationPublisher;
import com.staybooking.staybooking.repository.AccommodationPublisherRepository;
import com.staybooking.staybooking.repository.UserRepository;
import com.staybooking.staybooking.service.AccommodationPublisherService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccommodationPublisherServiceImpl implements AccommodationPublisherService {

    private AccommodationPublisherRepository accommodationPublisherRepository;

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccommodationPublisherServiceImpl(AccommodationPublisherRepository accommodationPublisherRepository, ModelMapper modelMapper, UserRepository userRepository){
        this.accommodationPublisherRepository = accommodationPublisherRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public APIResponse<UserInfo> createAccommodationPublisher(UserCreate accommodationPublisherToCreate) {
        AccommodationPublisher accommodationPublisher = modelMapper.map(accommodationPublisherToCreate, AccommodationPublisher.class);
        if(userRepository.existsByEmail(accommodationPublisher.getEmail())){
            throw new EmailAlreadyUsedException("Specified email is already used");
        }

        if(userRepository.existsByPhoneNumber(accommodationPublisherToCreate.getPhoneNumber())){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }
        AccommodationPublisher createdAccommodationPublisher = accommodationPublisherRepository.save(accommodationPublisher);
        UserInfo userInfo = modelMapper.map(createdAccommodationPublisher, UserInfo.class);


        return APIResponse.generateApiResponse(userInfo, HttpStatus.CREATED, "2001", "Accommodation publisher successful created");
    }

    @Override
    public APIResponse<UserInfo> updateAccommodationPublisher(Long id, UserUpdate accommodationPublisherToUpdate) {
        if(userRepository.existsByPhoneNumber(accommodationPublisherToUpdate.getPhoneNumber())){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }
        AccommodationPublisher accommodationPublisher = accommodationPublisherRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Accommodation publisher with specified id does not exist"));
        accommodationPublisher.setPhoneNumber(accommodationPublisherToUpdate.getPhoneNumber());
        accommodationPublisher.setFirstName(accommodationPublisherToUpdate.getFirstName());
        accommodationPublisher.setLastName(accommodationPublisherToUpdate.getLastName());
        UserInfo updatedAccommodationPublisher = modelMapper.map(accommodationPublisherRepository.save(accommodationPublisher), UserInfo.class);
        return APIResponse.generateApiResponse(updatedAccommodationPublisher, HttpStatus.OK, "2000", "Accommodation publisher successful updated");
    }

    @Override
    public APIResponse<UserInfo> findAccommodationPublisher(Long id) {
        AccommodationPublisher accommodationPublisher = accommodationPublisherRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Accommodation publisher with specified id does not exist"));
        UserInfo accommodationPublisherInfo = modelMapper.map(accommodationPublisher, UserInfo.class);
        return APIResponse.generateApiResponse(accommodationPublisherInfo, HttpStatus.OK, "2000", "Accommodation publisher successful found");
    }

    @Override
    public APIResponse<Boolean> blockAccommodationPublisher(Long id) {
        AccommodationPublisher accommodationPublisher = accommodationPublisherRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Accommodation publisher with specified id does not exist"));
        accommodationPublisher.setBlocked(true);
        accommodationPublisherRepository.save(accommodationPublisher);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Accommodation publisher successful blocked");
    }

    @Override
    public APIResponse<Boolean> unblockAccommodationPublisher(Long id) {
        AccommodationPublisher accommodationPublisher = accommodationPublisherRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Accommodation publisher with specified id does not exist"));
        accommodationPublisher.setBlocked(false);
        accommodationPublisherRepository.save(accommodationPublisher);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Accommodation publisher successful unblocked");
    }
}
