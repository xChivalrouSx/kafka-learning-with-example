package com.chivalrous.kafka.retryable.topic.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;
import org.springframework.kafka.retrytopic.SameIntervalTopicReuseStrategy;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final AppSettings appSettings;

    @Bean
    public RetryTopicConfiguration retryTopicConfiguration(KafkaTemplate<String, String> template) {
        return RetryTopicConfigurationBuilder.newInstance()
                .doNotAutoCreateRetryTopics()
                .suffixTopicsWithIndexValues()
                .maxAttempts(2)
                .exponentialBackoff(5_000, 3, 50_000_000)
                .sameIntervalTopicReuseStrategy(SameIntervalTopicReuseStrategy.MULTIPLE_TOPICS)
                .retryTopicSuffix(".custom-retry")
                .dltSuffix(".dlt")
                .excludeTopic("-")
                .create(template);
    }

}
