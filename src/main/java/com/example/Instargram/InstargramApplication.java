package com.example.Instargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InstargramApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstargramApplication.class, args);
	}

}
