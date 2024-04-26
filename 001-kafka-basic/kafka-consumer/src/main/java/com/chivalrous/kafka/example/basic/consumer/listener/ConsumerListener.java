package com.chivalrous.kafka.example.basic.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.chivalrous.kafka.example.basic.consumer.dto.CustomDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerListener {

	@KafkaListener(topics = "${com.chivalrous.kafka.topic}")
	public void consume(ConsumerRecord<String, CustomDto> payload) {
		log.info("Message: {}", payload.value());
	}
}
