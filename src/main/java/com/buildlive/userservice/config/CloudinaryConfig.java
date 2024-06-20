package com.buildlive.userservice.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name","dxq8esawh");
        config.put("api_key","624575134972664");
        config.put("api_secret","eLm7vMDHWpc993iUrSRapDcmHFo");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
