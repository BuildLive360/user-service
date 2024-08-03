package com.buildlive.userservice.service.impl;

import com.buildlive.userservice.entity.UserCredential;
import com.buildlive.userservice.entity.UserPosts;
import com.buildlive.userservice.repo.UserCredentialRepository;
import com.buildlive.userservice.repo.UserFeedRepository;
import com.buildlive.userservice.service.ICloudinaryService;
import com.buildlive.userservice.service.IUserFeedService;
import com.buildlive.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFeedImpl implements IUserFeedService{

    private final ICloudinaryService cloudinaryService;
    private final UserFeedRepository userFeedRepository;
    private final UserService userService;
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserPosts addFeed(String content, UUID userID, MultipartFile file) {

        Optional<UserCredential> userCredential = userCredentialRepository.findById(userID);
        if (userCredential.isPresent()){
            UserCredential user = userCredential.get();

            try{
                if (file.isEmpty()){
                    return null;
                }
                Map<?,?> uploadFile = cloudinaryService.upload(file,"user_feed");
                String imageUrl = (String) uploadFile.get("url");
                String publicId = (String) uploadFile.get("public_id");
                UserPosts userPosts = UserPosts.builder()
                        .imageUrl(imageUrl)
                        .imageId(publicId)
                        .content(content)
                        .createdDate(LocalDate.now())
                        .user(user).build();

                userFeedRepository.save(userPosts);
                return userPosts;
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public List<UserPosts> getAllFeeds() {
        return userFeedRepository.findAll();
    }
}
