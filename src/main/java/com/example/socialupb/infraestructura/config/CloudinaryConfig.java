package com.example.socialupb.infraestructura.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dcrka7yrk");
        config.put("api_key", "184818537858265");
        config.put("api_secret", "GlBkcattcuknUuxrWXig3aAff70");
        System.out.println("Cloudinary bean created");
        return new Cloudinary(config);
    }
}