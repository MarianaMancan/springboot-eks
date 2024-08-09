package com.example.springboot_eks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootEksApplication {

	@GetMapping("/greetings")
	public String message() {
		return "Utilizando AWS EKS";
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringbootEksApplication.class, args);
	}

}
