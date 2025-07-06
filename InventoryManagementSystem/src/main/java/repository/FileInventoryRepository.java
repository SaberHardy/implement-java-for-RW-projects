package main.java.repository;

import main.java.models.Product;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class FileInventoryRepository implements InventoryRepository {
    private final Map<String, Product> products = new HashMap<>();


    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void delete(String id) {
        products.remove(id);
    }

    @Override
    public void saveToFile(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(new ArrayList<>(products.values()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save inventory");
        }
    }

    @Override
    public void loadFromFile(String path) {

    }
}
