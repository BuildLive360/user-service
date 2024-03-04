package com.buildlive.userservice.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class UserInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID id;
	
	private String name;
	private String email;
	private String phone;
	private String password;
	private boolean isBlocked = false;
	private boolean isVerified;
	
	@Enumerated(EnumType.STRING)
	private Role roles;
	
	private String otp;
	private LocalDateTime expiryTime;
	private String image;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
