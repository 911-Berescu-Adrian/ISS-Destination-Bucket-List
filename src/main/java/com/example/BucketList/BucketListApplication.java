package com.example.BucketList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BucketListApplication {

	public static void main(String[] args) {
		SpringApplication.run(BucketListApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/login").allowedOrigins("http://localhost:8080");
				registry.addMapping("/destination").allowedOrigins("http://localhost:8080");
				registry.addMapping("/user").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
