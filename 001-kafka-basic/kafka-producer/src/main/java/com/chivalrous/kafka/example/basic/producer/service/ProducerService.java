package com.chivalrous.kafka.example.basic.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.chivalrous.kafka.example.basic.producer.config.Settings;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

	private final Settings settings;
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Object message) {
		log.info("Sending message to kafka. Topic: {}, Message: {}", settings.getKafkaTopic(), message);
		kafkaTemplate.send(settings.getKafkaTopic(), message);
	}

}
