package com.buildlive.userservice.service.impl;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.dto.VerifyDto;
import com.buildlive.userservice.entity.Role;
import com.buildlive.userservice.entity.UserCredential;
import com.buildlive.userservice.repo.UserCredentialRepository;
import com.buildlive.userservice.service.ICloudinaryService;
import com.buildlive.userservice.service.UserService;
import com.buildlive.userservice.service.client.AuthClient;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthClient authClient;

    @Autowired
    UserCredentialRepository userInfoRepository;

    @Autowired
    ICloudinaryService cloudinaryService;


    @Override
    public UserCredential saveUser(OtpDto otpDto) {
        UserCredential user = UserCredential.builder()
                .id(otpDto.getId())
                .name(otpDto.getName())
                .email(otpDto.getEmail())
                .password(otpDto.getPassword())
                .phone(otpDto.getPhone())
                .otp(otpDto.getOtp())
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

    @Override
    public List<UserCredential> findUsersByEmail(String email) {
            List < UserCredential> userList = userInfoRepository.findAll();
        List<UserCredential> matchingUsers = userList.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());

        if (!matchingUsers.isEmpty()) {
            return matchingUsers; // Return the matching user if found
        }

        // If no user matches the full email, return users whose emails start with the input
        return userList.stream()
                .filter(user -> user.getEmail().toLowerCase().startsWith(email.toLowerCase()))
                .collect(Collectors.toList());

    }

    @Override
    public UserCredential setProfilePhoto(String userId, MultipartFile file) {
        Optional<UserCredential> userProfileId = userInfoRepository.findById(UUID.fromString(userId));

        if (userProfileId.isPresent()){


            String folder = "profile_pictures";
            Map data = cloudinaryService.upload(file,folder);

            String imageUrl = (String) data.get("secure_url");
            String publicId = (String) data.get("public_id");

            UserCredential userProfile=userProfileId.get();
            userProfile.setImageUrl(imageUrl);
            userProfile.setImagePublicId(publicId);
            return userInfoRepository.save(userProfile);
        }
        return null;
    }

    @Override
    public String getUserPhoto(UUID userId) {
       UserCredential user =  userInfoRepository.findById(userId).get();
        return user.getImageUrl();
    }
}
