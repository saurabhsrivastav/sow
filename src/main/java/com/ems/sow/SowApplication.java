package com.ems.sow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SowApplication {

    public static void main(String[] args) {

        SpringApplication.run(SowApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS settings to all paths
                        .allowedOrigins("http://localhost:4200") // Allow multiple origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific HTTP methods
                        .allowedHeaders("Content-Type", "Authorization") // Allow specific headers
                        .allowCredentials(true); // Allow credentials (cookies, etc.)
            }
        };
    }

}
