package com.example.reading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/vedio/**").addResourceLocations("file:C:/reading_vedio/");
        registry.addResourceHandler("/audio/**").addResourceLocations("file:C:/reading_audio/");
        registry.addResourceHandler("/userAudio/**").addResourceLocations("file:C:/reading_read/");
    }
}