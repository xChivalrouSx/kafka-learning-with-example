package com.chivalrous.kafka.example.config;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.chivalrous.kafka.example.dto.CustomDto;

@Configuration
public class KafkaConfig {

	@Value("${com.chivalrous.kafka.server}")
	private String kafkaServer;

	@Bean
	public ProducerFactory<String, CustomDto> producerFactory() {
		return new DefaultKafkaProducerFactory<>(
				Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
						ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
						ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class));
	}

	@Bean
	public KafkaTemplate<String, CustomDto> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
