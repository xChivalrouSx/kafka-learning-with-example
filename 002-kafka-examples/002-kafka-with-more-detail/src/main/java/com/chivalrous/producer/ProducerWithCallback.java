package com.chivalrous.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.chivalrous.config.AppSettings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerWithCallback {

	public static void main(String[] args) {
		log.info("Producer running...");

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppSettings.KAFKA_SERVER);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>(AppSettings.KAFKA_DEMO_TOPIC, "Hello from Kafka Producer with callback!..");

		producer.send(producerRecord, (metadata, error) -> {
			if (error == null) {
				log.info("Procuder data sended. Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
						metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
			} else {
				log.error("Producer data could't be send. Error occured.", error);
			}
		});

		// Quick sended data defaultly go to same partition for performance.
		for (int i = 0; i < 10; i++) {
			producerRecord = new ProducerRecord<>(AppSettings.KAFKA_DEMO_TOPIC, "Kafka Producer Data - Same Partition - " + i);
			producer.send(producerRecord, (metadata, error) -> {
				if (error == null) {
					log.info("(Quick Send) Procuder data sended. Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
							metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
				} else {
					log.error("(Quick Send) Producer data could't be send. Error occured.", error);
				}
			});
		}

		// Slowly sended data defaultly go to different partition as working round robin.
		for (int i = 0; i < 10; i++) {
			producerRecord = new ProducerRecord<>(AppSettings.KAFKA_DEMO_TOPIC, "Kafka Producer Data - Different Partition - " + i);
			producer.send(producerRecord, (metadata, error) -> {
				if (error == null) {
					log.info("(Slow Send) Procuder data sended. Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
							metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
				} else {
					log.error("(Slow Send) Producer data could't be send. Error occured.", error);
				}
			});
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log.error("Thread sleeping error", e);
				Thread.currentThread().interrupt();
			}
		}

		producer.flush();
		producer.close();

		log.info("Producer data sended.");
	}

}
