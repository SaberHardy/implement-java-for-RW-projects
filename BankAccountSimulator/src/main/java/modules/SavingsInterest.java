package main.java.modules;

public final class SavingsInterest implements InterestStrategy {
    @Override
    public double calculateInterest(double balance) {
        return balance * 0.02; // 2% interest
    }
}
