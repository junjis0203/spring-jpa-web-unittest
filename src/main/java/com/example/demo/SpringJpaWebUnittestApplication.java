package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJpaWebUnittestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaWebUnittestApplication.class, args);
	}

	@Autowired(required = false)
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		if (userRepository != null) {
			userRepository.save(User.builder().name("太郎").mail("taro@examole.com").build());
			userRepository.save(User.builder().name("次郎").mail("jiro@examole.com").build());
			
			userRepository.findAll().forEach(System.out::println);
		}
	}

}
