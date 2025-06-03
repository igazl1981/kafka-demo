package dev.zsebel.kafka.consumer.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.zsebel.kafka")
public class EventConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventConsumerApplication.class, args);
    }
}
