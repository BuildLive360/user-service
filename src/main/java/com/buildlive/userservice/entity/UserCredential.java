package com.buildlive.userservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential {


	@Id
	private UUID id;
	
	private String name;
	private String email;
	private String phone;
	private String password;
	private boolean isBlocked;
	private boolean isVerified;
	

	@Enumerated(EnumType.STRING)
	private Role roles;
	
	private String otp;
	private LocalDateTime expiryTime;
	private String imageUrl;
	private String imagePublicId;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
