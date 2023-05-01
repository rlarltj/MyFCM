package com.example.mysse.mysse.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepository {
	public final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

	public SseEmitter save(String id, SseEmitter sseEmitter) {
		emitters.put(id, sseEmitter);
		return sseEmitter;
	}

	public void saveEventCache(String eventCacheId, Object event) {
		eventCache.put(eventCacheId, event);
	}

	public Map<String, SseEmitter> findAllStartWithById(String userId) {
		return emitters.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(userId))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public Map<String, Object> findAllEventCacheStartWithId(String userId) {
		return eventCache.entrySet().stream()
			.filter(entry -> entry.getKey().startsWith(userId))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public void deleteAllStartWithId(String userId) {
		emitters.forEach(
			(key, emitter) -> {
				if (key.startsWith(userId)) {
					emitters.remove(key);
				}
			}
		);
	}

	public void deleteById(String id) {
		emitters.remove(id);
	}

	public void deleteAllEventCacheStartWithId(String userId) {
		eventCache.forEach(
			(key, data) -> {
				if (key.startsWith(userId)) {
					eventCache.remove(key);
				}
			}
		);
	}
}
