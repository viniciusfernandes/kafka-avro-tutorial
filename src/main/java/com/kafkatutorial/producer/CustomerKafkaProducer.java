package com.kafkatutorial.producer;

import com.kafkatutorial.avro.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class CustomerKafkaProducer {
    @Autowired
    private KafkaTemplate<String, Customer> customerKafkaTemplate;

    @Value(value = "${kafka.topic.customer}")
    private String topicName;


    public void sendMessage(Customer customer) {
        var future = customerKafkaTemplate.send(topicName, customer.getFirstName(), customer);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Customer>>() {
            @Override
            public void onSuccess(SendResult<String, Customer> result) {
                System.out.println("Publicando o customer=[" + customer.getFirstName() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + customer.getFirstName() + "] due to : " + ex.getMessage());
            }
        });
    }
}
