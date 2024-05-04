package com.buildlive.userservice.service;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.dto.VerifyDto;
import com.buildlive.userservice.entity.UserCredential;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserCredential saveUser(OtpDto otpDto);

    UserCredential verify(VerifyDto otpRequest);

    UserCredential editUser(UUID id, UserCredential userCredential);

    List<UserCredential> findUsersByEmail(String email);

    default void getMethod(){
        System.out.println("sgbdvs");
    }
}
