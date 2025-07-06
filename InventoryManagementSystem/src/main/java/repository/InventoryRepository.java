package main.java.repository;

import main.java.models.Product;

import java.util.List;
import java.util.Optional;

/**
 * Handle Product Storage and retrieval
 * Concepts covered: Collections, Optional, File I/O
 * Using an interface allows you to define the required operations for inventory management without specifying how they are implemented.
 * This enables flexibility, easier testing, and the ability to swap out different storage mechanisms
 * (e.g., in-memory, file-based, database) without changing the rest of your code.
 */
public interface InventoryRepository {
    Optional<Product> findById(String id);

    List<Product> findAll();

    void save(Product product);

    void delete(String id);

    void saveToFile(String path);

    void loadFromFile(String path);
}
