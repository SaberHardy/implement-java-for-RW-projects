package main.java;

import main.java.models.BookModel;
import main.java.repository.BookInventoryRepository;
import main.java.repository.BookRepository;
import main.java.service.BookService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 * Concepts Practiced: Composition, Streams, Lambda
 * ○ Book borrowing/returning
 * ○ Search books (by author/title)
 * ○ Calculate late fees (use LocalDate)
 * ○ Serialize data with JSON (advanced)
 **/
public class mainClass {
    public static void main(String[] args) throws ParseException {
        // list all books in the library
        // save a book in the library
        // delete a book
        // update book
        // get book quantity

        BookRepository bookRepository = new BookInventoryRepository();
        BookService bookService = new BookService(bookRepository);

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
            scannerInput.nextLine();

            switch (inputValue) {
                case 1 -> listAllBooksInLibrary(bookService);
                case 2 -> saveBookInLibrary(bookService, scannerInput);
                case 3 -> updateBookInLibrary(bookService, scannerInput);
                case 4 -> System.out.println("choosed 4");
                case 5 -> System.out.println("choosed 5");
                default -> System.out.println("Please, enter a choice from the menu");
            }
        }
    }

    private static void listAllBooksInLibrary(BookService bookService) {
        if (bookService.findAllBooks().isEmpty()) {
            System.out.println("there are no books to borrow...");
        } else {
            System.out.println("WE have: ");
            bookService.findAllBooks().stream().forEach(System.out::println);
        }
    }

    private static void saveBookInLibrary(BookService bookService, Scanner scanner) throws ParseException {
        System.out.println("Enter Book name: ");
        String bookName = scanner.nextLine();

        System.out.println("Enter bookId: ");
        String bookId = scanner.nextLine();

        System.out.println("Enter bookAuthor name: ");
        String bookAuthor = scanner.nextLine();

        Date bookBorrowedDay = new Date();

        System.out.println("Enter bookQuantity: ");
        int bookQuantity = scanner.nextInt();
        bookService.saveBook(new BookModel(bookName, bookId, bookAuthor, bookBorrowedDay, bookQuantity));
    }

    private static BookModel findBookById(BookService bookService, int bookId) {
        return bookService.findAllBooks().get(bookId - 1);
    }

    private static boolean updateBookInLibrary(BookService bookService, Scanner scanner) {
        System.out.println("Which book you want to update ? ");
        int bookId = scanner.nextInt();
        if (bookId < 0) {
            throw new IllegalArgumentException("The book number should not be less than zero");
        }
        if (bookService.findAllBooks().isEmpty()) {
            throw new IllegalArgumentException("The list of the books is empty....!");
        } else {
            BookModel bookModelExists = findBookById(bookService, bookId);
            if (bookModelExists != null) {
                bookModelExists.setBookQuantity(39);
                System.out.println("we updated book quantity");
            }
        }
        return false;
    }
}
