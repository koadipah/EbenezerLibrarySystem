package utils;

import model.Book;
import java.util.*;

public class Searcher {
    public static Book linearSearchByISBN(List<Book> books, String isbn) {
        for (Book b : books) if (b.isbn.equals(isbn)) return b;
        return null;
    }
}