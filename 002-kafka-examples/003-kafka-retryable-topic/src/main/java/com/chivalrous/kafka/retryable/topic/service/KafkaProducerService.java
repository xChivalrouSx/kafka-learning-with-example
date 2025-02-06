package com.chivalrous.kafka.retryable.topic.service;

import com.chivalrous.kafka.retryable.topic.config.AppSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final AppSettings appSettings;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendDocumentCreatedMessage(String value) {
        kafkaTemplate.send(appSettings.getKafkaProducerTopic(), UUID.randomUUID().toString(), value);
        log.debug("Data was send. Data: {}", value);
    }

}
