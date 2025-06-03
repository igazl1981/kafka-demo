package dev.zsebel.kafka.producer.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Order;
import dev.zsebel.kafka.producer.OrderProducer;
import dev.zsebel.kafka.producer.generators.RandomOrderGenerator;

@Component
public class RandomOrderScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomOrderScheduler.class);

    private final RandomOrderGenerator randomOrderGenerator;
    private final OrderProducer orderProducer;

    @Autowired
    public RandomOrderScheduler(final RandomOrderGenerator randomOrderGenerator, final OrderProducer orderProducer) {
        this.randomOrderGenerator = randomOrderGenerator;
        this.orderProducer = orderProducer;
    }

    @Scheduled(fixedRate = 3000)
    public void schedule() {
        Order order = randomOrderGenerator.generate();
        orderProducer.send(order);
        LOGGER.info("Order sent: {}", order);
    }
}
