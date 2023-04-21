package com.example.myfcm.mysse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SSEController {

	@GetMapping("/sse")
	public String sse() {
		return "sse";
	}
}
