package com.chivalrous.kafka.example.basic.producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class Settings {

	@Value("${com.chivalrous.kafka.topic}")
	private String kafkaTopic;

}
