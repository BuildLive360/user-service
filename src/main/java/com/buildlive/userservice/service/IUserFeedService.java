package com.buildlive.userservice.service;

import com.buildlive.userservice.entity.UserPosts;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserFeedService {

    UserPosts addFeed(String content, UUID userID, MultipartFile file);

    List<UserPosts> getAllFeeds();
}
