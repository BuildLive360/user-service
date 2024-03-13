package com.buildlive.userservice.service.impl;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.dto.VerifyDto;
import com.buildlive.userservice.entity.Role;
import com.buildlive.userservice.entity.UserCredential;
import com.buildlive.userservice.repo.UserCredentialRepository;
import com.buildlive.userservice.service.UserService;
import com.buildlive.userservice.service.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthClient authClient;

    @Autowired
    UserCredentialRepository userInfoRepository;


    @Override
    public UserCredential saveUser(OtpDto otpDto) {
        UserCredential user = UserCredential.builder()
                .id(otpDto.getId())
                .name(otpDto.getName())
                .email(otpDto.getEmail())
                .password(otpDto.getPassword())
                .phone(otpDto.getPhone())
                .otp(otpDto.getOtp())
                .image(otpDto.getImage())
                .isBlocked(otpDto.isBlocked())
                .isVerified(otpDto.isVerified())
                .expiryTime(otpDto.getExpiryTime())
                .build();
        user.setRoles(Role.USER);


        return userInfoRepository.save(user);
    }

    @Override
    public UserCredential verify(VerifyDto otpRequest) {

        Optional <UserCredential> optionalUser = userInfoRepository.findById(otpRequest.getId());
        if(optionalUser.isPresent()){
            UserCredential user = optionalUser.get();
            user.setVerified(otpRequest.isVerified());
            return userInfoRepository.save(user);
        }
        else {
            throw  new RuntimeException("user Not found");
        }
    }

    @Override
    public UserCredential editUser(UUID id, UserCredential userCredential) {


        Optional<UserCredential> optionalUser =  userInfoRepository.findById(id);
        if(optionalUser.isPresent()){
            UserCredential user = optionalUser.get();
            user.setEmail(userCredential.getEmail());
            user.setName(userCredential.getName());
            user.setPhone(userCredential.getPhone());
            authClient.editUser(user);
            userInfoRepository.save(user);
            return user;
        }
        else {
            throw new RuntimeException("User NOt found");
        }
    }
}
