package com.uit.coursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.uit.coursemanagement.repository"
})
@EnableJpaAuditing(auditorAwareRef = "jpaAuditorProvider")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
