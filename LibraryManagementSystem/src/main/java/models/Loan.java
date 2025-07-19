package main.java.models;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public record Loan(BookModel bookModel,
                   Member member,
                   LocalDate borrowDate,
                   LocalDate dueDate,
                   Optional<LocalDate> returnDate) {
    public double calculateLateFees() {
        return returnDate.map(rd -> {
            long daysLate = ChronoUnit.DAYS.between(dueDate, rd);
            return daysLate > 0 ? daysLate * 0.5 : 0;
        }).orElse(0.0);
    }
}
