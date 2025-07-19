package main.java.repository;

import main.java.modules.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByNumber(String accountNumber);

    void save(Account account);
}
