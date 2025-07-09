package model;

import java.time.LocalDate;

public class Transaction {
    // Book ISBN involved in the transaction
    public String isbn;

     // ID of the borrower
    public String borrowerId;

     // Date the book was borrowed
    public LocalDate borrowDate;

    // Expected or actual return date
    public LocalDate returnDate;

     // Status: "borrowed" or "returned"
    public String status;

    public Transaction(String isbn, String borrowerId, LocalDate borrowDate, LocalDate returnDate, String status) {
        this.isbn = isbn;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
               ", Borrower ID: " + borrowerId +
               ", Borrowed: " + borrowDate +
               ", Due: " + returnDate +
               ", Status: " + status;
    }
}
