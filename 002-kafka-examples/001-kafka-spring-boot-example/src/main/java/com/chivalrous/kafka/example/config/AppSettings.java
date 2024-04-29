package com.chivalrous.kafka.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class AppSettings {

	@Value("${com.chivalrous.kafka.producer-topic}")
	private String kafkaProducerTopic;

	@Value("${com.chivalrous.kafka.consumer-topic}")
	private String kafkaConsumerTopic;

}
