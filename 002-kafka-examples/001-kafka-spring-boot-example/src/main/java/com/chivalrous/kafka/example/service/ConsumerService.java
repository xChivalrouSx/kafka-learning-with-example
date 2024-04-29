package com.chivalrous.kafka.example.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.chivalrous.kafka.example.config.AppSettings;
import com.chivalrous.kafka.example.dto.CustomDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {

	private final AppSettings appSettings;

	@KafkaListener(topics = "${com.chivalrous.kafka.consumer-topic}", groupId = "${com.chivalrous.kafka.consumer-group-id}", containerFactory = "magicalMessageListener")
	public void listen(CustomDto dto) {
		log.info("Message received for topic '{}'. Message Id: {}, Message Content: {}",
				appSettings.getKafkaConsumerTopic(), dto.getId(), dto.getMagicalMessage());
	}

}
