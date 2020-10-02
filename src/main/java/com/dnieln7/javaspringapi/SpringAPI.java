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
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
            }
        };
    }

    /**
     * Swagger configuration
     */
    @Configuration
    @EnableSwagger2
    class SpringFoxConfig {
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }
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
