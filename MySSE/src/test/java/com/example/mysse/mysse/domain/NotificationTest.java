package com.example.mysse.mysse.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.mysse.mysse.exception.InvalidNotificationContentException;

class NotificationTest {
	@Test
	@DisplayName("알림 내용이 공백일 경우 실패한다.")
	public void test1() throws Exception {
		//given

		//when, then
		Assertions.assertThrows(InvalidNotificationContentException.class, () -> new NotificationContent(" "));
	}

	@Test
	@DisplayName("알림 내용이 50글자 이상일 경우 실패한다.")
	public void test2() throws Exception {
		//given

		//when, then
		Assertions.assertThrows(InvalidNotificationContentException.class, () -> new NotificationContent("hh".repeat(30)));
	}

	@Test
	@DisplayName("알림 내용이 성공적으로 생성된다.")
	public void test3() throws Exception {
		//given

		//when, then
		Assertions.assertDoesNotThrow(() -> new NotificationContent("hihi"));
	}

	@Test
	@DisplayName("Type 정보를 가진 알림이 성공적으로 생성된다.")
	public void test4() throws Exception {
		//given
		NotificationContent content = new NotificationContent("content");
		User user = new User("user");

		//when, then

		Assertions.assertDoesNotThrow(() -> new Notification(content, false, user, NotificationType.CONNECT));
		Assertions.assertDoesNotThrow(() -> new Notification(content, false, user, NotificationType.APPROVE));
		Assertions.assertDoesNotThrow(() -> new Notification(content, false, user, NotificationType.REJECT));
	}
}