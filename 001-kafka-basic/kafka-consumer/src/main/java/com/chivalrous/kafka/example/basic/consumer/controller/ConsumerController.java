package com.chivalrous.kafka.example.basic.consumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("consumer")
@RequiredArgsConstructor
public class ConsumerController {

	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok().body("Up!..");
	}

}
