package com.chivalrous.kafka.retryable.topic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {
    
    @KafkaListener(containerFactory = "kafkaMessageListener",
            topics = "${com.chivalrous.kafka.topic}",
            groupId = "${com.chivalrous.kafka.consumer-group-id}")
    private void listen(String value, Acknowledgment ack) {
        try {
            log.debug("Data is received. Data: {}", value);
            throw new RuntimeException();
        } finally {
            ack.acknowledge();
        }
    }

}
