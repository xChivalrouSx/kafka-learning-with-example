package com.chivalrous.kafka.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chivalrous.kafka.example.dto.CustomDto;
import com.chivalrous.kafka.example.service.ProducerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("producer")
@RequiredArgsConstructor
public class ProducerController {

	private final ProducerService producerService;

	@PostMapping
	public ResponseEntity<String> produceJsonMessage(@RequestBody CustomDto json) {
		producerService.sendMessage(json);
		return ResponseEntity.ok().body("Success!..");
	}

}
