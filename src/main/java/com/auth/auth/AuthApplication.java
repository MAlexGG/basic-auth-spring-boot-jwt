package com.auth.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth.auth.model.User;
import com.auth.auth.repository.UserRepository;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User user = new User();
				user.setUsername("admin");
				user.setEmail("admin@mail.com");
				user.setPassword(bCryptPasswordEncoder.encode("pass"));
				userRepository.save(user);
			}
			if (userRepository.findByUsername("user").isEmpty()) {
				User user = new User();
				user.setUsername("user");
				user.setEmail("user@mail.com");
				user.setPassword(bCryptPasswordEncoder.encode("pass"));
				userRepository.save(user);
			}
		};
	}

}
