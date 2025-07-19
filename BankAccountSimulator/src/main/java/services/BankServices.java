package main.java.services;

import main.java.modules.Account;
import main.java.modules.Transaction;
import main.java.modules.TransactionType;
import main.java.repository.AccountRepository;
import main.java.utils.AccountNotFoundException;
import main.java.utils.BankException;
import main.java.utils.InsufficientFundsException;
import main.java.utils.InvalidTransactionException;

import java.util.List;
import java.util.Optional;

public class BankServices {
    private final AccountRepository accountRepository;

    public BankServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void processTransaction(String accountNumber,
                                   Transaction transaction,
                                   TransactionValidator validator) throws BankException {
        validator.validate(transaction); // polymorphic validation

        Account account = accountRepository.findByNumber(accountNumber).orElseThrow(AccountNotFoundException::new);

        if (transaction.type() == TransactionType.WITHDRAW && account.getBalance() < transaction.amount()) {
            throw new InsufficientFundsException();
        }
        account.addTransaction(transaction);
        accountRepository.save(account);
    }

    // Stream Processing
    public List<Transaction> getTransaction(String accountNumber, double minAmount) {
        return accountRepository.findByNumber(accountNumber)
                .map(account -> account.getTransactions().stream()
                        .filter(t -> t.amount() >= minAmount)
                        .toList())
                .orElse(List.of());
    }

    public Optional<Double> calculateInterest(String accountNumber) {
        return accountRepository.findByNumber(accountNumber).flatMap(Account::calculateInterest);
    }
}
