package utils;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import model.*;

public class FileManager {

    // Writes all book records to file
    public static void saveBooks(List<Book> books, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Book b : books) {
            writer.write("Title: " + b.title + "\n");
            writer.write("Author: " + b.author + "\n");
            writer.write("ISBN: " + b.isbn + "\n");
            writer.write("Category: " + b.category + "\n");
            writer.write("Year: " + b.year + "\n");
            writer.write("Publisher: " + b.publisher + "\n");
            writer.write("Shelf: " + b.shelfLocation + "\n");
            writer.write("---\n"); // Separator between books
        }
        writer.close();
    }

    // Writes all borrower records to file
    public static void saveBorrowers(Collection<Borrower> borrowers, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Borrower b : borrowers) {
            writer.write(String.join(",", b.name, b.id, b.contact, String.valueOf(b.fines)));
            writer.newLine();
        }
        writer.close();
    }

    // Writes all transaction records to file
    public static void saveTransactions(List<Transaction> transactions, String path) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (Transaction t : transactions) {
            writer.write(String.join(",", t.isbn, t.borrowerId,
                    t.borrowDate.toString(), t.returnDate.toString(), t.status));
            writer.newLine();
        }
        writer.close();
    }

    // Load Methods
    public static List<Book> loadBooks(String path) throws IOException {
        List<Book> books = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                Book b = new Book(parts[0], parts[1], parts[2], parts[3],
                        Integer.parseInt(parts[4]), parts[5], parts[6]);
                books.add(b);
            }
        }
        reader.close();
        return books;
    }
 
    // Reads borrower records from file and reconstructs objects
    public static List<Borrower> loadBorrowers(String path) throws IOException {
        List<Borrower> borrowers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                Borrower b = new Borrower(parts[0], parts[1], parts[2]);
                b.fines = Double.parseDouble(parts[3]);
                borrowers.add(b);
            }
        }
        reader.close();
        return borrowers;
    }

    public static List<Transaction> loadTransactions(String path) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                Transaction t = new Transaction(
                        parts[0],
                        parts[1],
                        LocalDate.parse(parts[2]),
                        LocalDate.parse(parts[3]),
                        parts[4]
                );
                transactions.add(t);
            }
        }
        reader.close();
        return transactions;
    }
}
