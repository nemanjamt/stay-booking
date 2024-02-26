package com.staybooking.staybooking.service.implementation;

import com.staybooking.staybooking.dto.response.APIResponse;
import com.staybooking.staybooking.dto.user.request.UserCreate;
import com.staybooking.staybooking.dto.user.request.UserUpdate;
import com.staybooking.staybooking.dto.user.response.UserInfo;
import com.staybooking.staybooking.exceptions.EmailAlreadyUsedException;
import com.staybooking.staybooking.exceptions.PhoneNumberAlreadyUsedException;
import com.staybooking.staybooking.model.users.Moderator;
import com.staybooking.staybooking.repository.ModeratorRepository;
import com.staybooking.staybooking.repository.UserRepository;
import com.staybooking.staybooking.service.ModeratorService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ModeratorServiceImpl implements ModeratorService {
    private final ModeratorRepository moderatorRepository;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ModeratorServiceImpl(ModeratorRepository moderatorRepository, UserRepository userRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.moderatorRepository = moderatorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public APIResponse<UserInfo> createModerator(UserCreate moderatorToCreate) {
        Moderator moderator = modelMapper.map(moderatorToCreate, Moderator.class);
        if(userRepository.existsByEmail(moderatorToCreate.getEmail())){
            throw new EmailAlreadyUsedException("Specified email is already used");
        }

        if(userRepository.existsByPhoneNumber(moderatorToCreate.getPhoneNumber())){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }

        Moderator createdModerator = moderatorRepository.save(moderator);
        UserInfo userInfo = modelMapper.map(createdModerator, UserInfo.class);
        return APIResponse.generateApiResponse(userInfo, HttpStatus.CREATED, "2001", "Moderator successful created");
    }

    @Override
    public APIResponse<UserInfo> updateModerator(Long id, UserUpdate moderatorToUpdate) {
        if(userRepository.existsByPhoneNumberAndIdNot(moderatorToUpdate.getPhoneNumber(), id)){
            throw new PhoneNumberAlreadyUsedException("Specified phone number is already used");
        }
        Moderator moderator = moderatorRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Moderator with specified id does not exist"));
        moderator.setFirstName(moderatorToUpdate.getFirstName());
        moderator.setLastName(moderatorToUpdate.getLastName());
        moderator.setPhoneNumber(moderatorToUpdate.getPhoneNumber());
        UserInfo updatedModerator = modelMapper.map(moderatorRepository.save(moderator), UserInfo.class);

        return APIResponse.generateApiResponse(updatedModerator, HttpStatus.OK, "2000", "Moderator successful updated");
    }

    @Override
    public APIResponse<Boolean> deleteModerator(Long id) {
        Moderator moderator = moderatorRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Moderator with specified id does not exist"));
        moderator.setDeleted(true);
        moderatorRepository.save(moderator);
        return APIResponse.generateApiResponse(Boolean.TRUE, HttpStatus.OK, "2000", "Moderator successful deleted");
    }

    @Override
    public APIResponse<UserInfo> findModerator(Long id) {
        Moderator moderator = moderatorRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("Moderator with specified id does not exist"));
        UserInfo moderatorInfo = modelMapper.map(moderator, UserInfo.class);
        return APIResponse.generateApiResponse(moderatorInfo, HttpStatus.OK, "2000", "Moderator successful deleted");
    }
}
