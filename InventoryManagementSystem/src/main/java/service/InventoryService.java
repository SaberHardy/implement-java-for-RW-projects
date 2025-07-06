package main.java.service;

import main.java.models.InventoryReport;
import main.java.repository.InventoryRepository;
import main.java.models.Product;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Optional<Product> findProduct(String id) {
        return repository.findById(id);
    }

    public void removeProduct(String id) {
        repository.delete(id);
    }

    public void stockProduct(String id, int quantity) {
        repository.findById(id).ifPresentOrElse(
                p -> p.updateQuantity(quantity),
                () -> {
                    throw new IllegalArgumentException("Product not found");
                }
        );
    }

    public InventoryReport generateReport() {
        var products = repository.findAll();
        return new InventoryReport(products.size(),
                products.stream().filter(product -> product.getQuantity() <= 0).count(),
                products.stream().collect(
                        Collectors.groupingBy(Product::getName,
                                Collectors.summingDouble(Product::getPrice))));
    }
}
