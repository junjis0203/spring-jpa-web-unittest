package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private static Map<String, User> userMap;

	@Before
	public void setUp() {
		userMap = new HashMap<>();
		userMap.put("1", User.builder().name("alice").mail("alice@example.com").build());
	}
	
	@TestConfiguration
	static class Config implements WebMvcConfigurer {

		@Override
		public void addFormatters(FormatterRegistry registry) {
			// emulate Spring Data JPA Web support
			registry.addConverter(String.class, User.class, id -> userMap.get(id));
		}
		
	}

	@Test
	public void userExists() throws Exception {
		mockMvc.perform(get("/users/{id}", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("name", is("alice")));
	}

	@Test
	public void userNotExists() throws Exception {
		mockMvc.perform(get("/users/{id}", "100"))
			.andExpect(status().isNotFound());
	}

}
