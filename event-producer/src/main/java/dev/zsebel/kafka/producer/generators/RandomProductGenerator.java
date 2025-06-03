package dev.zsebel.kafka.producer.generators;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dev.zsebel.kafka.domain.Product;

@Component
public class RandomProductGenerator {

    private static final Random RANDOM_NUMBER_GENERATOR = new Random();
    private static final List<String> PRODUCT_NAMES = List.of("Keyboard", "Mouse", "Monitor", "Laptop", "Headphones", "Charger", "Webcam", "Desk Lamp");

    public Product generate() {
        UUID productId = UUID.randomUUID();
        String productName = selectRandomProductName();
        int quantity = 1 + RANDOM_NUMBER_GENERATOR.nextInt(5);
        return new Product(productId.toString(), productName, quantity);
    }

    private String selectRandomProductName() {
        int productName = RANDOM_NUMBER_GENERATOR.nextInt(PRODUCT_NAMES.size());
        return PRODUCT_NAMES.get(productName);
    }
}
