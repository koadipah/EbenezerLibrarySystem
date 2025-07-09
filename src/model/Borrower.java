package model;

import java.util.*;

public class Borrower {
    // Borrower's full name
    public String name;
     // Unique borrower ID
    public String id;
     // List of ISBNs representing books currently borrowed
    public List<String> borrowedBooks;
      // Total fines owed by the borrower
    public double fines;
    public String contact;

    public Borrower(String name, String id, String contact) {
        this.name = name;
        this.id = id;
        this.contact = contact;
        this.borrowedBooks = new ArrayList<>();
        this.fines = 0.0;
    }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nID: " + id +
               "\nContact: " + contact +
               "\nBorrowed Books: " + (borrowedBooks.isEmpty() ? "None" : borrowedBooks) +
               "\nFines Owed: $" + String.format("%.2f", fines);
    }
}
