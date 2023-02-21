package com.wcci.reviews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ReviewsApplication {

    public static void main(String[] args) {
        // Hey, SpringApplication, do the needful for everything in the ReviewsApplication class.
        // For example, scan every class in this package (com.wcci.reviews) and subpackages for
        // Spring-related annotations. Once Spring has identified every component which have dependencies
        // and every component providing such dependencies, it hooks them altogether and runs the program.
        SpringApplication.run(ReviewsApplication.class, args);
    }

    // Hey, Spring, here's a "CommonsRequestLoggingFilter" implementation, which will log http requests like I want.
    // I expect that the RestController traffic will therefore be logged conveniently.
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    // public WebMvcConfigurer corsConfigurer() { return new WebMvcConfigurer() { @Override public void addCorsMappings(final CorsRegistry registry) { registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "OPTIONS", "PUT") .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers") .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials") .allowCredentials(true).maxAge(3600); } }; }
}
