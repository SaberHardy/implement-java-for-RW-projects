package main.java.modules;

import java.time.LocalDateTime;

// Concepts: Encapsulation (immutable), Validation
public record Transaction(
        String id,
        TransactionType type,
        double amount,
        LocalDateTime timestamp
) {
    public Transaction {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
    }
}
