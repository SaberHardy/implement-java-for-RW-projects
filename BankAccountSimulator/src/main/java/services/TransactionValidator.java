package main.java.services;

import main.java.modules.Transaction;
import main.java.utils.InvalidTransactionException;

@FunctionalInterface
public interface TransactionValidator {
    void validate(Transaction t) throws InvalidTransactionException;
}
