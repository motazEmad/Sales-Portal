package com.sp.customeridentityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableWebSecurity
public class CustomerIdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerIdentityServiceApplication.class, args);
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails admin = User.builder().username("admin")
				.password(passwordEncoder.encode("admin"))
				.roles("ADMIN")
				.build();
		UserDetails user = User.builder().username("user")
				.password(passwordEncoder.encode("user"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}
