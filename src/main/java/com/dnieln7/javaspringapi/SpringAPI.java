package com.dnieln7.javaspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main class
 *
 * @author dnieln7
 */
@SpringBootApplication
public class SpringAPI {

    public static void main(String[] args) {
        SpringApplication.run(SpringAPI.class, args);
    }

    /**
     * CORS configuration by endpoint.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/sellers").allowedOrigins("*");
                registry.addMapping("/sellers/{id}").allowedOrigins("*").allowedMethods("PUT", "GET", "DELETE");
                registry.addMapping("/products").allowedOrigins("*");
                registry.addMapping("/products/{id}").allowedOrigins("*").allowedMethods("PUT", "GET", "DELETE");
                registry.addMapping("/dashboard").allowedOrigins("*");
            }
        };
    }
}
