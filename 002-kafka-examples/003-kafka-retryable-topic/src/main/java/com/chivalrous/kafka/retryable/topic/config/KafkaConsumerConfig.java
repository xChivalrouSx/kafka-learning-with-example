package com.chivalrous.kafka.retryable.topic.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

	private static final String ENABLE_AUTO_COMMIT_CONFIG_VALUE = Boolean.FALSE.toString();
	private static final String AUTO_OFFSET_RESET_CONFIG_VALUE = "latest";

	private final AppSettings appSettings;

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appSettings.getKafkaServer(),
						ConsumerConfig.GROUP_ID_CONFIG, appSettings.getKafkaConsumerGroupId(),
						ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, ENABLE_AUTO_COMMIT_CONFIG_VALUE,
						ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET_CONFIG_VALUE,
						ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
						ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
		);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaMessageListener() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
