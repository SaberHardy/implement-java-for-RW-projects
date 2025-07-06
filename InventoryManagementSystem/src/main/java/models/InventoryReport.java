package main.java.models;

import java.util.Map;

/**
 * DTOs primarily serve to encapsulate and aggregate data fields.
 * They are designed to hold data and provide simple getter and setter methods for accessing and modifying this data.
 * Data Encapsulation:
 * DTOs primarily serve to encapsulate and aggregate data fields.
 * They are designed to hold data and provide simple getter and setter methods for accessing and modifying this data
 * No business logic
 * Decoupling
 * Serialization
 */
public record InventoryReport(long totalProducts, long outOfStockItems, Map<String, Double> valueByCategory) {
    /**
     * A record in Java is a special type of class that is used to hold immutable data and automatically provides
     * implementations for methods like equals, hashCode, and toString.
     * This record is used as a Data Transfer Object (DTO) to encapsulate inventory report data without any business logic
     */
}
