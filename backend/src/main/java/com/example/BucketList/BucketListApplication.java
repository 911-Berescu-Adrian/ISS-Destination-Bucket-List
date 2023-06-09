package com.example.BucketList;

import com.example.BucketList.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@Import(CorsConfig.class)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class BucketListApplication {

	public static void main(String[] args) {
		SpringApplication.run(BucketListApplication.class, args);
	}
}
