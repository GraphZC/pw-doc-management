package com.poolworldpattaya.docmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DocManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocManagementApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("addCorsMappings");
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                        .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }
}