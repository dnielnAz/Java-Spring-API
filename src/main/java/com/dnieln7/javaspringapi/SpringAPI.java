package com.dnieln7.javaspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.OffsetDateTime;
import java.util.Optional;

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

    /**
     * Auditing configuration
     */
    @Configuration
    @EnableJpaAuditing(auditorAwareRef = "auditor", dateTimeProviderRef = "date")
    class AuditConfig {

        @Bean(name = "date")
        public DateTimeProvider dateTimeProvider() {
            return () -> Optional.of(OffsetDateTime.now());
        }

        @Bean(name = "auditor")
        public AuditorAware<String> auditorProvider() {
            return () -> Optional.of("admin");
        }
    }
}
