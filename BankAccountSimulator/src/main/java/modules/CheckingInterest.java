package main.java.modules;

public final class CheckingInterest implements InterestStrategy {
    @Override
    public double calculateInterest(double balance) {
        return balance * 0.001; // 0.001 interest
    }
}
