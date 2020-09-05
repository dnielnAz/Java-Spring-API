package com.dnieln7.javaspringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
}
