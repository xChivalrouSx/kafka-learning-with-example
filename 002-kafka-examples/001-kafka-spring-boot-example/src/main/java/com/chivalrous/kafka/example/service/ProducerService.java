package com.chivalrous.kafka.example.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.chivalrous.kafka.example.config.AppSettings;
import com.chivalrous.kafka.example.dto.CustomDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

	private final AppSettings appSettings;
	private final KafkaTemplate<String, CustomDto> kafkaTemplate;

	public void sendMessage(CustomDto dto) {
		log.info("Sending message to kafka. magicalNumber: {}, Message: {}", dto.getId(), dto.getMagicalMessage());
		kafkaTemplate.send(appSettings.getKafkaProducerTopic(), dto);
	}

}
