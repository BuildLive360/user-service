package com.buildlive.userservice.controller;

import com.buildlive.userservice.entity.UserPosts;
import com.buildlive.userservice.service.IUserFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserPostController {


    @Autowired
    IUserFeedService userFeedService;
    @PostMapping(value = "/posts/add-feed")
    public ResponseEntity<?> addFeed(@RequestParam("content") String content,
                                     @RequestParam("userId") UUID userId,
                                     @RequestPart("file") MultipartFile file){
        return ResponseEntity.ok(userFeedService.addFeed(content,userId,file));
    }

    @GetMapping("/posts/getAll")
    public ResponseEntity<List<UserPosts>> userFeeds(){
        return new ResponseEntity<>(userFeedService.getAllFeeds(), HttpStatus.OK);
    }
}
