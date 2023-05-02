package com.example.myfcm.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myfcm.dto.NotificationRequest;
import com.example.myfcm.service.FcmService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FcmController {

	private final FcmService fcmService;

	@PostMapping("/api/fcm")
	public ResponseEntity pushMessage(@RequestBody NotificationRequest notificationRequest) throws IOException {
		fcmService.sendMessageTo(
			notificationRequest.getTargetToken(),
			notificationRequest.getTitle(),
			notificationRequest.getBody());
		return ResponseEntity.ok().build();
	}
}

