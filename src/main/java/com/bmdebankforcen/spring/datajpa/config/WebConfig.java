package com.bmdebankforcen.spring.datajpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permettre l'accès à toutes les URL
                .allowedOrigins("*")  // Autoriser l'accès depuis toutes les origines
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Autoriser ces méthodes HTTP
                .allowedHeaders("*");  // Autoriser tous les en-têtes HTTP
    }
}