package com.buildlive.userservice.service;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.dto.VerifyDto;
import com.buildlive.userservice.entity.UserCredential;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserCredential saveUser(OtpDto otpDto);

    UserCredential verify(VerifyDto otpRequest);

    UserCredential editUser(UUID id, UserCredential userCredential);

    List<UserCredential> findUsersByEmail(String email);

    UserCredential setProfilePhoto(String userId, MultipartFile file);

    String getUserPhoto(UUID userId);
}
