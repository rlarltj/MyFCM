package com.example.myfcm.mysse.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.myfcm.mysse.domain.User;
import com.example.myfcm.mysse.repository.UserRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
		mockMvc.perform(get("/subscribe")
			.param("id", String.valueOf(user.getId())))
			.andExpect(status().isOk());
	}
}