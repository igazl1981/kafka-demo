package dev.zsebel.kafka.domain;

import java.time.LocalDateTime;
import java.util.List;

public record Order(Long orderId, List<Product> products, LocalDateTime orderDateTime) {}
