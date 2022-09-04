package com.kafkatutorial.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PromotionalEmailKafkaConsumer {
    @KafkaListener(
            topics = "${kafka.topic.customer}",
            groupId = "${kafka.group.market}",
            containerFactory = "marketKafkaConsumerFactory")
    public void consumeMessage(String message) {
        System.out.println("Firing promotional email to the customer: " + message.toUpperCase());
    }
}
