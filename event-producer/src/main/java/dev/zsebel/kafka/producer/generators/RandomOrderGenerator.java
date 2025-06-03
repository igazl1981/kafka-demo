package dev.zsebel.kafka.producer.generators;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Order;
import dev.zsebel.kafka.domain.Product;

@Component
public class RandomOrderGenerator {

    private static final int MINIMUM_NUMBER_OF_PRODUCTS = 1;
    private static final int MAXIMUM_NUMBER_OF_PRODUCTS = 5;

    private final Random random = ThreadLocalRandom.current();
    private final AtomicInteger orderId = new AtomicInteger(0);
    private final RandomProductGenerator randomProductGenerator;

    @Autowired
    RandomOrderGenerator(final RandomProductGenerator randomProductGenerator) {
        this.randomProductGenerator = randomProductGenerator;
    }

    public Order generate() {
        List<Product> orderedProducts = generateRandomProducts();
        return new Order((long) orderId.incrementAndGet(), orderedProducts, LocalDateTime.now());
    }

    private List<Product> generateRandomProducts() {
        int numberOfProducts = random.nextInt(MAXIMUM_NUMBER_OF_PRODUCTS) + MINIMUM_NUMBER_OF_PRODUCTS;
        return Stream.generate(randomProductGenerator::generateProduct)
            .limit(numberOfProducts)
            .toList();
    }
}
