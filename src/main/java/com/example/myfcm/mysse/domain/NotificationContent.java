package com.example.myfcm.mysse.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.example.myfcm.exception.InvalidNotificationContentException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationContent {

	private static final int MAX_LENGTH = 50;

	@Column(nullable = false, length = MAX_LENGTH)
	private String content;

	public NotificationContent(String content) {
		if (isNotValidNotificationContent(content)) {
			throw new InvalidNotificationContentException();
		}
		this.content = content;
	}

	private boolean isNotValidNotificationContent(String content) {
		return Objects.isNull(content) || content.isBlank() ||
			content.length() > MAX_LENGTH;
	}
}
