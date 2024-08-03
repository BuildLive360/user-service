package com.buildlive.userservice.repo;

import com.buildlive.userservice.entity.UserSubscribedPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserSubscribedPlanRepo extends JpaRepository<UserSubscribedPlan, UUID> {

    boolean existsByUserIdAndPlanId(UUID userId, UUID planId);
}
