package com.kafkatutorial.producer;

import com.kafkatutorial.avro.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OrderKafkaProducer {
    @Autowired
    private KafkaTemplate<String, Order> orderKafkaTemplate ;

    @Value(value = "${kafka.topic.customer}")
    private String topicName;


    public void sendMessage(Order order) {
        ListenableFuture<SendResult<String, Order>> future = orderKafkaTemplate .send(topicName, order);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {

            @Override
            public void onSuccess(SendResult<String, Order> result) {
                System.out.println("Sent Order=[" + order +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send Order=["
                        + order + "] due to : " + ex.getMessage());
            }
        });
    }
}
