package main.java.repository;

import main.java.models.BookModel;

import java.util.*;

public class BookInventoryRepository implements BookRepository {
    private final Map<String, BookModel> bookModelMap = new HashMap<>();

    @Override
    public Optional<BookModel> findById(String id) {
        return Optional.ofNullable(bookModelMap.get(id));
    }

    @Override
    public List<BookModel> findAll() {
        return new ArrayList<>(bookModelMap.values());
    }

    @Override
    public void save(BookModel bookModel) {
        bookModelMap.put(bookModel.getBookId(), bookModel);
    }

    @Override
    public void deleteBook(String id) {
        bookModelMap.remove(id);
    }

    @Override
    public void updateBook(String id, BookModel bookModel) {
        bookModelMap.put(id, bookModel);
    }
}
