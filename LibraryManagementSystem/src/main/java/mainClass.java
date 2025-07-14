package main.java;

import java.util.Scanner;

/**
 * Concepts Practiced: Composition, Streams, Lambda
 * ○ Book borrowing/returning
 * ○ Search books (by author/title)
 * ○ Calculate late fees (use LocalDate)
 * ○ Serialize data with JSON (advanced)
 **/
public class mainClass {
    public static void main(String[] args) {
        // list all books in the library
        // save a book in the library
        // delete a book
        // update book
        // get book quantity
        Scanner scannerInput = null;
        try {
            scannerInput = new Scanner(System.in);
        } catch (NumberFormatException ex) {
            System.err.println("Error: Invalid Number format. " + ex.getMessage());
        }
        while (true) {
            System.out.println("\n---------------- Library Books ----------------\n");
            System.out.println("\t\tWelcome in Your Magic Library\n");
            System.out.println("Enter your Choice: ");
            System.out.println("   1. Show all books we have");
            System.out.println("   2. Save a book");
            System.out.println("   3. Update a book");
            System.out.println("   4. See how many Books we have");
            System.out.println("   5. Delete a Book");

            int inputValue = scannerInput.nextInt();
            switch (inputValue) {
                case 1 -> listAllBooksInLibrary();
                case 2 -> System.out.println("choosed 2");
                case 3 -> System.out.println("choosed 3");
                case 4 -> System.out.println("choosed 4");
                case 5 -> System.out.println("choosed 5");
                default -> System.out.println("Please, enter a choice from the menu");
            }
        }
    }
}
