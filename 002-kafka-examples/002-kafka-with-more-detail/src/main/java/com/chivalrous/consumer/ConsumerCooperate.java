package com.chivalrous.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.chivalrous.config.AppSettings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerCooperate {

	public static void main(String[] args) {
		log.info("Consumer running...");

		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppSettings.KAFKA_SERVER);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, AppSettings.CONSUMER_GROUP_ID);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, CooperativeStickyAssignor.class.getName());

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				log.info("Shutdown detected.");
				consumer.wakeup();
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					log.error("Error occured.", e);
					Thread.currentThread().interrupt();
				}
			}

		});

		try {
			consumer.subscribe(Arrays.asList(AppSettings.KAFKA_DEMO_TOPIC));

			while (true) {
				log.info("Pooling...");
				ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					log.info("Key: {}, Value: {}, Partition: {}, Offset: {}" + consumerRecord.key(),
							consumerRecord.value(), consumerRecord.partition(), consumerRecord.offset());
				}
			}
		} catch (WakeupException e) {
			log.info("Consumer is starting to shut down.");
		} catch (Exception e) {
			log.error("Unexpected exception in the consumer.", e);
		} finally {
			consumer.close();
			log.info("Consumer shutting down completed.");
		}
	}

}
