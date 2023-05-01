package com.example.mysse.mysse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.mysse.mysse.domain.User;
import com.example.mysse.mysse.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	/**
	 * 로그인 한 유저 sse 연결
	 */
	@GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
	@ResponseStatus(HttpStatus.OK)
	public SseEmitter subscribe(@PathVariable Long id,
		@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {

		if (id == 10) {
			notificationService.send(new User(100L), "알람입니다 ㅎㅇㅎㅇ");
		}
		return notificationService.subscribe(id, lastEventId);
	}


}
