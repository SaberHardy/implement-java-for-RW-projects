package main.java.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class Represents a product in inventory with validation
 * Concepts: Encapsulation, Immutability, Validation
 */
public class Product implements Serializable {
    private final String id;
    private final String name;
    private final double price;
    private int quantity;

    public Product(String id, String name, double price, int quantity) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Invalid Id passed");
        if (price <= 0) throw new IllegalArgumentException("The price should be positive");

        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.price = price;
        this.quantity = quantity;
    }

    // Creating only the getters without setters, bcz we don't need setters for immutable fields
    public String getId() {return id;}
    public double getPrice() {return price;}
    public String getName() {return name;}
    public int getQuantity() {return quantity;}

    public void updateQuantity(int delta){
        if(this.quantity + delta <0){
            throw new IllegalArgumentException("Insufficient Quantity passed");
        }
        this.quantity += quantity;
    }

    /**
     * The equals method overrides the default "Object.equals" to provide a custom equality check for Product objects.
     * It returns true if the given object is also a Product and has the same id as the current instance.
     * This allows two Product objects to be considered equal if their id fields match.
     */
    @Override
    public boolean equals(Object o) {
        // This line is to check if the current object instance and the one passed is the same or no in the memory.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        // The hashCode() method overrides the default implementation from Object to provide a hash code,
        // and this is based on the id field of the Product class.
        // This ensures that two Product objects with the same id will have the same hash code,
        // which is important for correct behavior in hash-based collections like HashSet or HashMap
        return Objects.hash(id);
    }
}
