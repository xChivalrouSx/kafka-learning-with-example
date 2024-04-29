package com.chivalrous.kafka.example.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.chivalrous.kafka.example.dto.CustomDto;

@Configuration
public class KafkaConsumerConfig {

	@Value("${com.chivalrous.kafka.server}")
	private String kafkaServer;

	@Value("${com.chivalrous.kafka.consumer-group-id}")
	private String kafkaConsumerGroupId;

	@Bean
	public ConsumerFactory<String, CustomDto> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(
				Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
						ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroupId,
						ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
						ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class),
				new StringDeserializer(), new JsonDeserializer<>(CustomDto.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CustomDto> magicalMessageListener() {
		ConcurrentKafkaListenerContainerFactory<String, CustomDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
