package com.chivalrous.kafka.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("system")
@RequiredArgsConstructor
public class SystemController {

	@GetMapping("health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok().body("Up!..");
	}

}
