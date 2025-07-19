package main.java.modules;

//Concepts: Polymorphism (Strategy Pattern), Functional Interface
@FunctionalInterface
public interface InterestStrategy {
    double calculateInterest(double balance);
}

