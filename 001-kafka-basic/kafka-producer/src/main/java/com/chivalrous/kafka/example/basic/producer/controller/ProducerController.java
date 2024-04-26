package com.chivalrous.kafka.example.basic.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chivalrous.kafka.example.basic.producer.dto.CustomDto;
import com.chivalrous.kafka.example.basic.producer.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("producer")
@RequiredArgsConstructor
public class ProducerController {

	private final ProducerService producerService;

	@GetMapping("/health")
	public ResponseEntity<String> checkHealth() {
		return ResponseEntity.ok().body("Up!..");
	}

	@PostMapping("/json-message")
	public ResponseEntity<String> produceJsonMessage(@RequestBody CustomDto json) {
		producerService.sendMessage(json);
		return ResponseEntity.ok().body("Success!..");
	}

}
