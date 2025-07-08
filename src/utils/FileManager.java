package utils;

import model.*;
import java.io.*;
import java.util.*;

public class FileManager {
    public static void saveBooks(List<Book> books, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Book b : books) {
            writer.write(String.join(",", b.title, b.author, b.isbn, b.category, String.valueOf(b.year), b.publisher, b.shelfLocation));
            writer.newLine();
        }
        writer.close();
    }

    public static void saveBorrowers(Collection<Borrower> borrowers, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Borrower b : borrowers) {
            writer.write(String.join(",", b.name, b.id, b.contact, String.valueOf(b.fines)));
            writer.newLine();
        }
        writer.close();
    }

    public static void saveTransactions(List<Transaction> transactions, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Transaction t : transactions) {
            writer.write(String.join(",", t.isbn, t.borrowerId, t.borrowDate.toString(), t.returnDate.toString(), t.status));
            writer.newLine();
        }
        writer.close();
    }
}

