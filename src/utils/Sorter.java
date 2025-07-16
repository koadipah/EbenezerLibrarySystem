package utils;

import model.Book;
import java.util.*;

public class Sorter {
    // Sorts books by title using merge sort (divide and conquer)
    // Stable and efficient for large datasets (O(n log n))
    public static void mergeSortByTitle(List<Book> books) {
        if (books.size() <= 1) return;
        int mid = books.size() / 2;
        List<Book> left = new ArrayList<>(books.subList(0, mid));
        List<Book> right = new ArrayList<>(books.subList(mid, books.size()));

        mergeSortByTitle(left);
        mergeSortByTitle(right);

        books.clear();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).title.compareTo(right.get(j).title) < 0) {
                books.add(left.get(i++));
            } else {
                books.add(right.get(j++));
            }
        }
        while (i < left.size()) books.add(left.get(i++));
        while (j < right.size()) books.add(right.get(j++));
    }
}