package main.java.repository;

import main.java.modules.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Concepts: Abstraction (interface), Optional
public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    @Override
    public Optional<Account> findByNumber(String accountNumber) {
        return Optional.ofNullable(accounts.get(accountNumber));
    }

    @Override
    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }
}
