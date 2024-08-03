package com.buildlive.userservice.repo;

import com.buildlive.userservice.entity.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserFeedRepository extends JpaRepository<UserPosts, UUID> {
}
