package com.buildlive.userservice.service.client;

import com.buildlive.userservice.dto.OtpDto;
import com.buildlive.userservice.entity.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTH-SERVICE",url = "http://3.132.56.186:8070")
public interface AuthClient {

        @PostMapping("/api/v1/auth/register")
        OtpDto getUser();

        @PutMapping("/api/v1/auth/users/edit-user")
        ResponseEntity<UserCredential> editUser(@RequestBody UserCredential userCredential);

}
