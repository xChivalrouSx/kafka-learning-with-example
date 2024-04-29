package com.chivalrous.kafka.example.config;

import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonSerde;

@EnableKafka
@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

	@Value("${com.chivalrous.kafka.server}")
	private String kafkaServer;

	@Value("${com.chivalrous.kafka.streams-app-id}")
	private String kafkaStreamsAppId;

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration kafkaStreamsConfig() {
		return new KafkaStreamsConfiguration(Map.of(
				StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer,
				StreamsConfig.APPLICATION_ID_CONFIG, kafkaStreamsAppId,
				StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
				StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName()));

	}

}
