package dev.zsebel.kafka.producer.generators;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Order;
import dev.zsebel.kafka.domain.Product;

@Component
public class RandomOrderGenerator {

    private final AtomicInteger orderId = new AtomicInteger(0);

    private final RandomProductGenerator randomProductGenerator;

    @Autowired
    public RandomOrderGenerator(final RandomProductGenerator randomProductGenerator) {
        this.randomProductGenerator = randomProductGenerator;
    }

    public Order generate() {
        // Temporarily disabled for testing
        // UUID orderId = UUID.randomUUID();
        int exclusiveUpperBound = new Random().nextInt(5) + 2;
        List<Product> orderedProducts = IntStream.range(1, exclusiveUpperBound)
            .mapToObj(i -> randomProductGenerator.generate())
            .collect(Collectors.toList());
        return new Order(String.valueOf(orderId.incrementAndGet()), orderedProducts);
    }
}
