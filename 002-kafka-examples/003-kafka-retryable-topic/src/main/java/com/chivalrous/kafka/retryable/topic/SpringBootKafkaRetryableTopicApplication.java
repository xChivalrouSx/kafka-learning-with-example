package com.chivalrous.kafka.retryable.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringBootKafkaRetryableTopicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaRetryableTopicApplication.class, args);
	}

}
