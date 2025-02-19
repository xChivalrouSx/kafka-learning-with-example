package com.chivalrous.kafka.retryable.topic.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

	private final AppSettings appSettings;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(
				Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appSettings.getKafkaServer(),
						ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
						ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class));
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(KafkaProducerInterceptor defaultProducerInterceptor) {
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
		kafkaTemplate.setProducerInterceptor(defaultProducerInterceptor);
		return kafkaTemplate;
	}

}
