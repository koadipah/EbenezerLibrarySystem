package service;

import model.Book;
import utils.Sorter;
import utils.Searcher;
import java.util.*;

public class BookInventory {
    private Map<String, List<Book>> categoryMap = new HashMap<>();
    private List<Book> allBooks = new ArrayList<>();

    public void addBook(Book book) {
        allBooks.add(book);
        categoryMap.putIfAbsent(book.category, new ArrayList<>());
        categoryMap.get(book.category).add(book);
    }

    public void removeBook(String isbn) {
        allBooks.removeIf(b -> b.isbn.equals(isbn));
        categoryMap.values().forEach(list -> list.removeIf(b -> b.isbn.equals(isbn)));
    }

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

    public Book searchByISBN(String isbn) {
        return Searcher.linearSearchByISBN(allBooks, isbn);
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.title.toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.author.toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> filterByCategory(String category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }

    public void sortBooksByTitle() {
        Sorter.mergeSortByTitle(allBooks);
    }

    public void sortBooksByYear() {
        allBooks.sort(Comparator.comparingInt(b -> b.year));
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }
}
