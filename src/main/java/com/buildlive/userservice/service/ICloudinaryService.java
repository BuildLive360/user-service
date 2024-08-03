package com.buildlive.userservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ICloudinaryService {

    public Map<?,?> upload(MultipartFile file, String folder);
//    public Map<String, Object> uploadVideo(MultipartFile file, String folder);
}
