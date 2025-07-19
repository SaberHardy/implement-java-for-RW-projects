package main.java.utils;

public class AccountNotFoundException extends BankException {
    public AccountNotFoundException() {
        super("Account Not Found");
    }
}
