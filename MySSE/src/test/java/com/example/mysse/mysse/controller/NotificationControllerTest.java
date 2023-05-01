package com.example.mysse.mysse.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.mysse.mysse.domain.User;
import com.example.mysse.mysse.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NotificationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	void setup() {
		user = userRepository.findById(100L).get();
	}
	@Test
	@DisplayName("SSE에 연결을 진행한다.")
	public void subscribe() throws Exception {
		//given

		//when, then
		mockMvc.perform(get("/subscribe", user.getId()))
			.andExpect(status().isOk());
	}
}