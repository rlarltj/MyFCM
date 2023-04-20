package com.example.myfcm.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myfcm.dto.NotificationRequest;
import com.example.myfcm.service.FCMService;
import com.example.myfcm.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationApiController {

	private final NotificationService notificationService;
	private final FCMService fcmService;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody String token) {
		notificationService.register(1L, token);
		return ResponseEntity.ok().build();
	}

}
