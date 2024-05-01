package com.chivalrous.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.chivalrous.config.AppSettings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerWithKey {

	public static void main(String[] args) {
		log.info("Producer running...");

		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppSettings.KAFKA_SERVER);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		// Sended data with same key go to same partition.
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 10; i++) {
				String key = "key_" + i;
				ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
						AppSettings.KAFKA_DEMO_TOPIC,
						key,
						"Kafka Producer Data - With Key - " + i);
				producer.send(producerRecord, (metadata, error) -> {
					if (error == null) {
						log.info("Procuder data sended. Topic: {}, Key: {}, Partition: {}, Offset: {}, Timestamp: {}",
								metadata.topic(), key, metadata.partition(), metadata.offset(), metadata.timestamp());
					} else {
						log.error("Producer data could't be send. Error occured.", error);
					}
				});
			}
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
