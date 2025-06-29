package com.geek.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  If Spring Security is on the classpath,
 *  Spring Boot automatically secures all HTTP endpoints with “basic” authentication. However, you can further customize the security settings.
 */
@SpringBootApplication
public class SpringBootSecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityServiceApplication.class, args);
	}

}
