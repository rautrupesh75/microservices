package com.example.userms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * swagger-ui url: http://localhost:8080/swagger-ui/index.html
 */
@SpringBootApplication
@RestController
public class UsermsApplication {

	@GetMapping("/hello")
	public String getHello() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(UsermsApplication.class, args);
	}

}
