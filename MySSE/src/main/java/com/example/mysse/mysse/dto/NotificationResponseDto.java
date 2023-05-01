package com.example.mysse.mysse.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationResponseDto {
	private String content;

	public NotificationResponseDto(String content) {
		this.content = content;
	}
}
