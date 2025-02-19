package com.chivalrous.kafka.retryable.topic.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class KafkaProducerInterceptor implements ProducerInterceptor<String, String> {

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> producerRecordMap) {
        producerRecordMap.headers().remove(KafkaHeaders.EXCEPTION_STACKTRACE);
        return producerRecordMap;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info(metadata.topic());
        // No need to implement
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // No need to implement
    }

    @Override
    public void close() {
        // No need to implement
    }

}