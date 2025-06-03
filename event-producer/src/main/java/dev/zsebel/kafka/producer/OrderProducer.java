package dev.zsebel.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Order;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String topic;

    @Autowired
    public OrderProducer(final KafkaTemplate<String, Order> kafkaTemplate, @Value("${spring.kafka.topic}") final String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(final Order order) {
        kafkaTemplate.send(topic, order);
    }
}
