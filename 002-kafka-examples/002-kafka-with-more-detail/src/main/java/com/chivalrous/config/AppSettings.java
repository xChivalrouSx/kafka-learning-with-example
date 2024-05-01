package com.chivalrous.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppSettings {

	public static final String KAFKA_SERVER = "localhost:9092";
	public static final String KAFKA_DEMO_TOPIC = "kafka-demo";

}
