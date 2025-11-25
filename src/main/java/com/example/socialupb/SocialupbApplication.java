package com.example.socialupb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication (scanBasePackages = "com.example.socialupb")
@EnableJpaRepositories(basePackages = "com.example.socialupb.infraestructura.repository")
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EntityScan("com.example.socialupb.infraestructura.entity")
@EnableAsync
@EnableJpaAuditing
public class SocialupbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialupbApplication.class, args);
	}

}
