package main.java;

import main.java.repository.InMemoryAccountRepository;
import main.java.utils.InvalidTransactionException;
import main.java.repository.AccountRepository;
import main.java.modules.SavingsInterest;
import main.java.modules.TransactionType;
import main.java.services.BankServices;
import main.java.modules.Transaction;
import main.java.utils.BankException;
import main.java.modules.Account;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class bankMain {
    public static void main(String[] args) {
        AccountRepository repository = new InMemoryAccountRepository();
        BankServices service = new BankServices(repository);
        Scanner scanner = new Scanner(System.in);

        // Setup test account
        Account account = new Account("12345", new SavingsInterest());
        repository.save(account);

        while (true) {
            System.out.println("\n1. Deposit\n2. Withdraw\n3. View Transactions\n4. Calculate Interest\n5. Exit");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> deposit(service, scanner);
//                    case 2 -> withdraw(service, scanner);
//                    case 3 -> viewTransactions(service, scanner);
//                    case 4 -> calculateInterest(service, scanner);
                    case 5 -> System.exit(0);
                }
            } catch (BankException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void deposit(BankServices service, Scanner scanner) throws BankException {
        System.out.print("Enter account number: ");
        String accNum = scanner.next();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        service.processTransaction(
                accNum,
                new Transaction(UUID.randomUUID().toString(),
                        TransactionType.DEPOSIT,
                        amount,
                        LocalDateTime.now()),
                t -> {
                    if (t.amount() > 10_000)
                        throw new InvalidTransactionException("Large deposit requires verification");
                }
        );
    }
}