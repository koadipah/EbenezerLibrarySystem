package model;

import java.time.LocalDate;

public class Transaction {
    public String isbn;
    public String borrowerId;
    public LocalDate borrowDate;
    public LocalDate returnDate;
    public String status;

    public Transaction(String isbn, String borrowerId, LocalDate borrowDate, LocalDate returnDate, String status) {
        this.isbn = isbn;
        this.borrowerId = borrowerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }
}
