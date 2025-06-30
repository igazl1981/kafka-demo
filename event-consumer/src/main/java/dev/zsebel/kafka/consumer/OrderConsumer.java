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

    @KafkaListener(topics = "orders")
    public void consume(ConsumerRecord<Long, Order> consumerRecord) {
        Order order = consumerRecord.value();
        LOGGER.info(
            "Received event - topic: {}, partition: {}, orderId: {}, productCount: {}",
            consumerRecord.topic(),
            consumerRecord.partition(),
            order.orderId(),
            order.products().size()
        );
    }
}
