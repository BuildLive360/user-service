package com.buildlive.userservice.dto;

import com.buildlive.userservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {


    private UUID id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private boolean isBlocked;
    private boolean isVerified;
    private Role roles;
    private String otp;
    private LocalDateTime expiryTime;
    private String image;

}
