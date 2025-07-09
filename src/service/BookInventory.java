package service;

import java.util.*;
import model.Book;
import utils.Searcher;
import utils.Sorter;

public class BookInventory {
    // Map groups books by category for quick filtered access
    private Map<String, List<Book>> categoryMap = new HashMap<>();
    // List holds all books for global operations (listing, sorting, searching)
    private List<Book> allBooks = new ArrayList<>();

    // Adds a book to both the all-books list and the appropriate category list
    public void addBook(Book book) {
        allBooks.add(book);
        categoryMap.putIfAbsent(book.category, new ArrayList<>());
        categoryMap.get(book.category).add(book);
    }

    // Removes a book by ISBN from all books and category-specific lists
    public void removeBook(String isbn) {
        allBooks.removeIf(b -> b.isbn.equals(isbn));
        categoryMap.values().forEach(list -> list.removeIf(b -> b.isbn.equals(isbn)));
    }

     // Lists all books in the inventory
    public void listBooks() {
        if (allBooks.isEmpty()) {
            System.out.println(" ");
            System.out.println("No books available in the inventory‼️ .");
            System.out.println("Please add books to the inventory first‼️ .\n");
            return;
        }
        System.out.println("Listing all books in the inventory:");
        System.out.println(" ");
        for (Book b : allBooks) System.out.println(b);
        System.out.println();
    }

    // Uses linear search to find a book by ISBN
    public Book searchByISBN(String isbn) {
        return Searcher.linearSearchByISBN(allBooks, isbn);
    }

    // Searches all books by title (case-insensitive substring match)
    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Searches all books by author (case-insensitive substring match)
    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.author.toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

     // Returns books under a specific category using map lookup
    public List<Book> filterByCategory(String category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }

    // Sorts books by title using merge sort
    public void sortBooksByTitle() {
        Sorter.mergeSortByTitle(allBooks);
    }

      // Sorts books by publication year (ascending)    
    public void sortBooksByYear() {
        allBooks.sort(Comparator.comparingInt(b -> b.year));
    }

     // Returns the entire list of books
    public List<Book> getAllBooks() {
        return allBooks;
    }
}
