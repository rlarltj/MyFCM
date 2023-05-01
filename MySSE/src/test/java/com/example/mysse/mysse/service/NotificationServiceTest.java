package com.example.mysse.mysse.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mysse.mysse.domain.User;
import com.example.mysse.mysse.repository.UserRepository;

@SpringBootTest
@Transactional
class NotificationServiceTest {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserRepository userRepository;

	private User user;
	
	@BeforeEach
	void setup() {
		user = userRepository.findById(100L).get();
	}

	@Test
	@DisplayName("알림 구독을 진행한다.")
	public void subscribe() throws Exception {
		//given
		String lastEventId = "";

		//when, then
		Assertions.assertDoesNotThrow(() -> notificationService.subscribe(user.getId(), lastEventId));
	}

	@Test
	@DisplayName("알림 메세지를 전송한다.")
	public void send() throws Exception {
		//given

		String lastEventId = "";
		notificationService.subscribe(user.getId(), lastEventId);

		//when, then
		Assertions.assertDoesNotThrow(() -> notificationService.send(user, "알람이요."));
	}
}