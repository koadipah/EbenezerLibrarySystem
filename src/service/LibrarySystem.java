package service;

import model.*;
import utils.FileManager;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    private BookInventory inventory = new BookInventory();
    private BorrowerRegistry registry = new BorrowerRegistry();
    private LendingTracker tracker = new LendingTracker();
    private OverdueMonitor monitor = new OverdueMonitor(registry);

    public void run() {
        loadAllData();  // ⬅️ Load data at startup

        Scanner scanner = new Scanner(System.in);
        try {
            String title = " EBENEZER LIBRARY BOOK LENDING & INVENTORY MANAGEMENT SYSTEM ";
            int width = 100;
            System.out.println("\n" + " ".repeat((width - title.length()) / 2) + title + "\n");

            while (true) {
                System.out.println("Please select an option by entering the corresponding number:");
                System.out.println("1. Add a New Book");
                System.out.println("2. List All Books");
                System.out.println("3. Search Book by ISBN");
                System.out.println("4. Register a New Borrower");
                System.out.println("5. Borrow a Book");
                System.out.println("6. Display Overdue Books");
                System.out.println("7. Save Current Data");
                System.out.println("8. Generate Reports");
                System.out.println("9. Sort/Search Options");
                System.out.println("10. Lookup Borrower by ID");
                System.out.println("11. Return a Book");
                System.out.println("12. View All Transactions");
                System.out.println("13. Exit the System");
                System.out.print("Enter your choice (1–13): \n");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 13.\n");
                    continue;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter book details below:");
                        System.out.print("Title: ");
                        String titleInput = scanner.nextLine();
                        System.out.print("Author: ");
                        String author = scanner.nextLine();

                        String isbn;
                        while (true) {
                            System.out.print("ISBN (numbers only): ");
                            isbn = scanner.nextLine().trim();
                            if (isbn.matches("\\d+")) break;
                            System.out.println("Invalid ISBN. Please enter numbers only.\n");
                        }

                        System.out.print("Category: ");
                        String category = scanner.nextLine();
                        System.out.print("Year: ");
                        int year;
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year. Book entry cancelled.\n");
                            break;
                        }

                        System.out.print("Publisher: ");
                        String publisher = scanner.nextLine();
                        System.out.print("Shelf: ");
                        String shelf = scanner.nextLine();
                        Book b = new Book(titleInput, author, isbn, category, year, publisher, shelf);
                        inventory.addBook(b);
                        System.out.println("Book added successfully!\n");
                    }
                    case 2 -> inventory.listBooks();
                    case 3 -> {
                        System.out.print("Enter ISBN to search: ");
                        String searchIsbn = scanner.nextLine().trim();
                        if (!searchIsbn.matches("\\d+")) {
                            System.out.println("Invalid ISBN format. Only numbers are allowed.\n");
                            break;
                        }
                        System.out.println(" ");
                        Book found = inventory.searchByISBN(searchIsbn);
                        System.out.println(found != null ? found : "Not found");
                        System.out.println(); // prints empty line after result
                    }
                    case 4 -> {
                        System.out.println("Enter Borrower Information:");
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Contact: ");
                        String contact = scanner.nextLine();
                        registry.addBorrower(new Borrower(name, id, contact));
                        System.out.println("Borrower registered successfully!\n");
                    }
                    case 5 -> {
                        System.out.println("Enter Borrow Details:");
                        System.out.print("ISBN: ");
                        String borrowISBN = scanner.nextLine();
                        if (!borrowISBN.matches("\\d+")) {
                            System.out.println("Invalid ISBN format. Borrow cancelled.\n");
                            break;
                        }
                        System.out.print("Borrower ID: ");
                        String borrowerId = scanner.nextLine();
                        Transaction t = new Transaction(borrowISBN, borrowerId, LocalDate.now(), LocalDate.now().plusDays(14), "borrowed");
                        tracker.borrow(t);
                        monitor.add(t);
                        System.out.println("Borrow transaction recorded successfully!\n");
                        System.out.println("Overdue books will be monitored and reported.\n");
                        System.out.println("Please remember to return the book on time to avoid fines❌❌.\n");
                    }
                    case 6 -> monitor.getOverdue().forEach(System.out::println);
                    case 7 -> saveAllData();
                    case 8 -> generateReports();
                    case 9 -> {
                        while (true) {
                            System.out.println("\nSort/Search Options:");
                            System.out.println("1. Sort by Title");
                            System.out.println("2. Sort by Year");
                            System.out.println("3. Search by Title");
                            System.out.println("4. Search by Author");
                            System.out.print("Choose option (1–4): ");

                            String input = scanner.nextLine().trim();
                            int sortOption;
                            try {
                                sortOption = Integer.parseInt(input);
                                if (sortOption < 1 || sortOption > 4) {
                                    System.out.println("Invalid option. Please enter a number between 1 and 4.\n");
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter numbers only (1–4).\n");
                                continue;
                            }

                            switch (sortOption) {
                                case 1 -> {
                                    inventory.sortBooksByTitle();
                                    System.out.println("Books sorted by title.\n");
                                    inventory.listBooks();
                                }
                                case 2 -> {
                                    inventory.sortBooksByYear();
                                    System.out.println("Books sorted by year.\n");
                                    inventory.listBooks();
                                }
                                case 3 -> {
                                    System.out.print("Enter title to search: ");
                                    String t = scanner.nextLine();
                                    List<Book> matches = inventory.searchByTitle(t);
                                    if (matches.isEmpty()) System.out.println("No matching books found.\n");
                                    else matches.forEach(System.out::println);
                                }
                                case 4 -> {
                                    System.out.print("Enter author to search: ");
                                    String a = scanner.nextLine();
                                    List<Book> matches = inventory.searchByAuthor(a);
                                    if (matches.isEmpty()) System.out.println("No matching books found.\n");
                                    else matches.forEach(System.out::println);
                                }
                            }
                            break;
                        }
                    }
                    case 10 -> {
                        System.out.print("Enter Borrower ID to lookup: ");
                        String id = scanner.nextLine();
                        if (!id.matches("\\d+")) {
                            System.out.println("Invalid ID format. Please enter numbers only.\n");
                            break;
                        }
                        Borrower borrower = registry.getBorrowerById(id);
                        if (borrower == null) {
                            System.out.println("Borrower not found.\n");
                        } else {
                            System.out.println("Borrower Details:");
                            System.out.println(borrower);
                            System.out.println();
                        }
                    }
                    case 11 -> {
                        System.out.println("Enter Return Details:");
                        System.out.print("ISBN: ");
                        String returnISBN = scanner.nextLine();
                        if (!returnISBN.matches("\\d+")) {
                            System.out.println("Invalid ISBN format. Return cancelled.\n");
                            break;
                        }
                        System.out.print("Borrower ID: ");
                        String borrowerId = scanner.nextLine();
                        Transaction returnTransaction = new Transaction(
                                returnISBN, borrowerId, LocalDate.now(), LocalDate.now(), "returned"
                        );
                        tracker.returnBook(returnTransaction);
                        System.out.println("Return transaction recorded.\n");
                    }
                    case 12 -> {
                        List<Transaction> txns = tracker.getTransactions();
                        if (txns.isEmpty()) {
                            System.out.println("No transactions recorded.\n");
                        } else {
                            System.out.println("All Transactions:");
                            txns.forEach(System.out::println);
                        }
                    }
                    case 13 -> {
                        System.out.println("Goodbye\n");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please enter a number between 1 and 13.\n");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void loadAllData() {
        try {
            FileManager.loadBooks("books.txt").forEach(inventory::addBook);
            FileManager.loadBorrowers("borrowers.txt").forEach(registry::addBorrower);
            for (Transaction t : FileManager.loadTransactions("transactions.txt")) {
                tracker.borrow(t);
                monitor.add(t);
            }
            System.out.println("Data loaded successfully.\n");
        } catch (IOException e) {
            System.out.println("Warning: Failed to load some data files. Starting fresh.\n");
        }
    }

    private void saveAllData() {
        try {
            FileManager.saveBooks(inventory.getAllBooks(), "books.txt");
            FileManager.saveBorrowers(registry.getAllBorrowers(), "borrowers.txt");
            FileManager.saveTransactions(tracker.getTransactions(), "transactions.txt");
            System.out.println("Data saved successfully.\n");
        } catch (IOException e) {
            System.out.println("Failed to save: " + e.getMessage() + "\n");
        }
    }

    private void generateReports() {
        Map<String, Long> borrowCounts = tracker.getTransactions().stream()
                .collect(Collectors.groupingBy(t -> t.isbn, Collectors.counting()));
        System.out.println("Generating Reports...\n");
        System.out.println("Most Borrowed Books:");
        borrowCounts.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(5)
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

        System.out.println("\nTop Fined Borrowers:");
        registry.getAllBorrowers().stream()
                .sorted((a, b) -> Double.compare(b.fines, a.fines))
                .limit(5)
                .forEach(b -> System.out.println(b.name + ": $" + b.fines));

        System.out.println("\nInventory by Category:");
        inventory.getAllBooks().stream()
                .collect(Collectors.groupingBy(b -> b.category, Collectors.counting()))
                .forEach((cat, count) -> System.out.println(cat + ": " + count));
        System.out.println();        
    }
}
