package main.java.models;

import java.util.Date;

public class BookModel {
    String bookName;
    String bookId;
    String bookAuthor;
    Date bookBorrowedDay;

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    int bookQuantity;

    public BookModel(String bookName, String bookId, String bookAuthor, Date bookBorrowedDay, int bookQuantity) {
        if (!bookName.isEmpty()) {
            this.bookName = bookName;
        } else {
            throw new IllegalArgumentException("The book name should be provided");
        }
        this.bookId = bookId;
        if (!bookAuthor.isEmpty()) {
            this.bookAuthor = bookAuthor;
        } else {
            throw new IllegalArgumentException("The Author name should be provided");
        }
        this.bookBorrowedDay = bookBorrowedDay;
        if (bookQuantity > 0) this.bookQuantity = bookQuantity;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getBookBorrowedDay() {
        return bookBorrowedDay;
    }

    public int getBookQuantity() {
        if (bookQuantity < 0) {
            throw new IllegalArgumentException("The quantity should be greater than Zero!");
        } else {
            return bookQuantity;
        }
    }

    public String toString() {
        return "Name: '" + this.bookName + "', Author: '" + this.bookAuthor + "', Quantity: '" + this.bookQuantity + "'";
    }

    public int updateBookQuantity(int bookQuantityToTake) {
        if (bookQuantityToTake > 0 && bookQuantity > bookQuantityToTake) {
            bookQuantity -= bookQuantityToTake;
        }
        return bookQuantity;
    }
}
