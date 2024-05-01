package com.chivalrous.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.chivalrous.config.AppSettings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Producer {

	public static void main(String[] args) {
		log.info("Producer running...");

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppSettings.KAFKA_SERVER);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>(AppSettings.KAFKA_DEMO_TOPIC, "Hello from Kafka Producer!..");
		producer.send(producerRecord);
		producer.flush();
		producer.close();

		log.info("Producer data sended.");
	}

}
