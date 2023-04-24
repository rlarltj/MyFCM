package com.example.myfcm.mysse.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.myfcm.mysse.domain.Notification;
import com.example.myfcm.mysse.domain.User;
import com.example.myfcm.mysse.dto.NotificationResponseDto;
import com.example.myfcm.mysse.repository.EmitterRepository;
import com.example.myfcm.mysse.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	private final NotificationRepository notificationRepository;
	private final EmitterRepository emitterRepository;

	public SseEmitter subscribe(Long memberId, String lastEventId) {
		String emitterId = makeTimeIncludeId(memberId);
		SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
		emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
		emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

		// 503 에러를 방지하기 위한 더미 이벤트 전송
		String eventId = makeTimeIncludeId(memberId);
		sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + memberId + "]");

		// 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
		if (hasLostData(lastEventId)) {
			sendLostData(lastEventId, memberId, emitterId, emitter);
		}

		return emitter;
	}

	private String makeTimeIncludeId(Long memberId) {
		return memberId + "_" + System.currentTimeMillis();
	}

	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
		log.info("sendNotification");

		try {
			emitter.send(SseEmitter.event()
				.id(eventId)
				.data(data));
		} catch (IOException exception) {
			emitterRepository.deleteById(emitterId);
		}
	}

	private boolean hasLostData(String lastEventId) {
		return !lastEventId.isEmpty();
	}

	private void sendLostData(String lastEventId, Long memberId, String emitterId, SseEmitter emitter) {
		Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithId(String.valueOf(memberId));
		eventCaches.entrySet().stream()
			.filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
			.forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
	}

	public void send(User receiver, String content) {
		Notification notification = notificationRepository.save(createNotification(receiver, content));

		String receiverId = String.valueOf(receiver.getId());

		log.info("receiver Id = {}", receiverId);
		String eventId = receiverId + "_" + System.currentTimeMillis();
		Map<String, SseEmitter> emitters = emitterRepository.findAllStartWithById(receiverId);
		emitters.forEach(
			(key, emitter) -> {
				emitterRepository.saveEventCache(key, notification);
				sendNotification(emitter, eventId, key, new NotificationResponseDto(notification.getContent()));
			}
		);
	}

	private Notification createNotification(User receiver, String content) {
		return new Notification(content, false, receiver);
	}
}
