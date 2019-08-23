package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/users/{id}")
	public User show(@PathVariable("id") User user) {
		if (user == null) {
			@ResponseStatus(HttpStatus.NOT_FOUND)
			class NotFoundException extends RuntimeException {
				private static final long serialVersionUID = 1L;
			}
			throw new NotFoundException();
		}
		
		return user;
	}

}
