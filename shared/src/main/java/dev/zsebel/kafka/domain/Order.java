package dev.zsebel.kafka.domain;

import java.util.List;

public record Order(String orderId, List<Product> products) {}
