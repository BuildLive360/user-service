package com.buildlive.userservice.service.impl;

import com.buildlive.userservice.service.ICloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl  implements ICloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public Map<?,?> upload(MultipartFile file, String folder) {

        try{
            Map<String,Object> options = ObjectUtils.asMap("folder",folder);
            Map<String,Object> data = cloudinary.uploader().upload(file.getBytes(),options);
            String imageUrl = (String)  data.get("secure_url");
            return data;
        }
        catch (IOException e){
            throw new RuntimeException("Image uploading failed");
        }

    }

//    @Override
//    public Map<String, Object> uploadVideo(MultipartFile file, String folder) {
//        return null;
//    }
}
