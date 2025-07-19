package main.java.utils;

public class InsufficientFundsException extends BankException {
    public InsufficientFundsException() { super("Insufficient funds"); }
}
