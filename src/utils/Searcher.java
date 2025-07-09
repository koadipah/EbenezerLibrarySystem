package utils;

import java.util.*;
import model.Book;

public class Searcher {
    // Performs linear search to find a book by ISBN
    // Time complexity: O(n) — not optimal for large datasets
    public static Book linearSearchByISBN(List<Book> books, String isbn) {
        for (Book b : books) if (b.isbn.equals(isbn)) return b;
        return null;
    }
}