package com.buildlive.userservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscribedPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserCredential user;

    private UUID planId;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private boolean isPurchased;
    private String transactionId;



}
