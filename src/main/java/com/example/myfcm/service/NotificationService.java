package com.example.myfcm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.myfcm.dto.NotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

	private final Map<Long, String> tokenMap = new HashMap<>();
	private final FCMService fcmService;

	public void register(final Long userId, final String token) {
		tokenMap.put(userId, token);
	}

	public void deleteToken(final Long userId) {
		tokenMap.remove(userId);
	}

	public String getToken(final Long userId) {
		return tokenMap.get(userId);
	}

	public void sendNotification(final NotificationRequest request) {
		try {
			fcmService.send(request);
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
		}
	}
}
