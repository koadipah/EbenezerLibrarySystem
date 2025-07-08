package model;

public class Book {
    public String title;
    public String author;
    public String isbn;
    public String category;
    public int year;
    public String publisher;
    public String shelfLocation;

    public Book(String title, String author, String isbn, String category, int year, String publisher, String shelfLocation) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.year = year;
        this.publisher = publisher;
        this.shelfLocation = shelfLocation;
    }

    public String toString() {
        return String.format("%s by %s (%d) - ISBN: %s [%s]", title, author, year, isbn, category);
    }
}
