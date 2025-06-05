package dev.zsebel.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Order;

@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "orders", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<Long, Order> record) {
        Order order = record.value();
        LOGGER.info(
            "Received event - topic: {}, partition: {}, orderId: {}, productCount: {}",
            record.topic(),
            record.partition(),
            order.orderId(),
            order.products().size()
        );
    }
}
