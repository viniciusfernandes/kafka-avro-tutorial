package com.kafkatutorial;

import com.kafkatutorial.avro.Customer;
import com.kafkatutorial.producer.CustomerKafkaProducer;
import com.kafkatutorial.producer.OrderKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class KafkaAvroApplication implements CommandLineRunner {
    @Autowired
    private CustomerKafkaProducer customerKafkaProducer;

    @Autowired
    private OrderKafkaProducer orderKafkaProducer;

    public static void main(String[] args) {
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println("\nKAFKA AVRO TUTORIAL\n");
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXX");
        SpringApplication.run(KafkaAvroApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var customer = Customer.newBuilder()
                .setAge(44)
                .setHeight(165.0f)
                .setAutomatedEmail(true)
                .setFirstName("Vinicius")
                .setLastName("Fernandes")
                .setWeight(61f).build();
        customerKafkaProducer.sendMessage(customer);
        // orderKafkaProducer.sendMessage(new Order());

    }
}
