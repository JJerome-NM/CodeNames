package com.codenames.config;

import com.codenames.properties.CodeNamesProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
//@RequiredArgsConstructor
//public class RestControllersConfig implements WebMvcConfigurer {
//
//    private final CodeNamesProperties codeNamesProperties;
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(codeNamesProperties.getRestController().getCrossOrigin())
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*");
//    }
//}
