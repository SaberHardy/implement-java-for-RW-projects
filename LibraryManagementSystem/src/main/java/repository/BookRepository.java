package main.java.repository;

import main.java.models.BookModel;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<BookModel> findById(String id);

    // get all the books
    List<BookModel> findAll();

    // save a book
    void save(BookModel bookModel);

    // delete a book
    void deleteBook(String id);

    // update a book
    void updateBook(String id, BookModel bookModel);
}
