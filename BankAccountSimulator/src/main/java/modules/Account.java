package main.java.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Concepts: Composition (Account has Transactions), Optional, Encapsulation
public class Account {
    private final String accountNumber;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();
    private InterestStrategy interestStrategy;

    public Account(String accountNumber, InterestStrategy strategy) {
        this.accountNumber = accountNumber;
        this.interestStrategy = strategy;
    }

    // Encapsulated mutation
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        if (transaction.type() == TransactionType.DEPOSIT) {
            this.balance += transaction.amount();
        } else {
            this.balance -= transaction.amount();
        }
    }

    public Optional<Double> calculateInterest() {
        return Optional.ofNullable(interestStrategy)
                .map(strategy -> strategy.calculateInterest(balance));
    }

    // Getters (no setters for critical fields)
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
