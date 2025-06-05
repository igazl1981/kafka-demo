package dev.zsebel.kafka.producer.generators;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Product;

@Component
public class RandomProductGenerator {

    private static final Random RANDOM_NUMBER_GENERATOR = ThreadLocalRandom.current();
    private static final List<String> PRODUCT_NAMES = List.of("Keyboard", "Mouse", "Monitor", "Laptop", "Headphones", "Charger", "Webcam", "Desk Lamp");
    private static final int MINIMUM_QUANTITY = 1;
    private static final int MAXIMUM_QUANTITY = 5;

    public Product generateProduct() {
        UUID productId = UUID.randomUUID();
        String productName = selectRandomProductName();
        int quantity = MINIMUM_QUANTITY + RANDOM_NUMBER_GENERATOR.nextInt(MAXIMUM_QUANTITY);
        return new Product(productId.toString(), productName, quantity);
    }

    private String selectRandomProductName() {
        int productName = RANDOM_NUMBER_GENERATOR.nextInt(PRODUCT_NAMES.size());
        return PRODUCT_NAMES.get(productName);
    }
}
