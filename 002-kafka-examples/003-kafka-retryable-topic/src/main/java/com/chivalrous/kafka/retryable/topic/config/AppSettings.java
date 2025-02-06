package com.chivalrous.kafka.retryable.topic.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppSettings {

	@Value("${com.chivalrous.kafka.server}")
	private String kafkaServer;

	@Value("${com.chivalrous.kafka.topic}")
	private String kafkaProducerTopic;

	@Value("${com.chivalrous.kafka.consumer-group-id}")
	private String kafkaConsumerGroupId;

}
