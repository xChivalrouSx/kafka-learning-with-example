package com.chivalrous.kafka.retryable.topic.controller;

import com.chivalrous.kafka.retryable.topic.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/produce/{message}")
    public void sendMessage(@PathVariable String message) {
        kafkaProducerService.sendDocumentCreatedMessage(message);
    }
}
