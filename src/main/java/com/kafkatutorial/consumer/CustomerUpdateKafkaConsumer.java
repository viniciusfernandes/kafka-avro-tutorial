package com.kafkatutorial.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomerUpdateKafkaConsumer {
    @KafkaListener(
            topics = "${kafka.topic.customer}",
            groupId = "${kafka.group.financial}",
            containerFactory = "financialKafkaConsumerFactory")
    public void consumeMessage(String message) {
        System.out.println("Updating customer \"" + message + "\" last access date: " + new Date());
    }
}
