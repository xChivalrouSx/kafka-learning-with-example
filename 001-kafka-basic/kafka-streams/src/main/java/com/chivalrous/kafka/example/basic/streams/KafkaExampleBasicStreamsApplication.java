package com.chivalrous.kafka.example.basic.streams;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;

public class KafkaExampleBasicStreamsApplication {

	private static Logger log = Logger.getLogger(KafkaExampleBasicStreamsApplication.class.getName());

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-basic-streams-example");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC, "earliest");

		StreamsBuilder streamsBuilder = new StreamsBuilder();
		streamsBuilder.<String, String>stream("message")
				.flatMapValues((readOnlyKey, value) -> Arrays.asList(value.split(" ")))
				.groupBy((key, value) -> value)
				.count(Materialized.with(Serdes.String(), Serdes.Long()))
				.toStream()
				.to("word-count", Produced.with(Serdes.String(), Serdes.Long()));

		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), props);
		kafkaStreams.start();
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

		log.info("Kafka Streams started...");
	}

}
