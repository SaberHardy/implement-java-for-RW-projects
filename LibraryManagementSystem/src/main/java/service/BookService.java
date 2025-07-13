package main.java.service;

import main.java.models.BookModel;
import main.java.repository.BookRepository;

import java.util.List;
import java.util.Objects;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = Objects.requireNonNull(bookRepository);
    }

    public List<BookModel> findAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(BookModel bookModel) {
        bookRepository.save(bookModel);
    }

    public void deleteBook(String id) {
        bookRepository.deleteBook(id);
    }

    public void updateBook(String id, BookModel bookModel) {
        bookRepository.updateBook(id, bookModel);
    }
}
