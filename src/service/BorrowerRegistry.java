package service;

import java.util.*;
import model.Borrower;

public class BorrowerRegistry {
    // Map is used for constant-time lookup of borrowers by ID
    private Map<String, Borrower> borrowerMap = new HashMap<>();

     // Adds a borrower using their ID as the key
    public void addBorrower(Borrower borrower) {
        borrowerMap.put(borrower.id, borrower);
    }
    // Retrieves a borrower using recursive search over the values
    public Borrower getBorrowerById(String id) {
        return recursiveSearch(new ArrayList<>(borrowerMap.values()), id, 0);
    }

    // Recursively searches for a borrower in the list by ID
    private Borrower recursiveSearch(List<Borrower> list, String id, int index) {
        if (index >= list.size()) return null;
        if (list.get(index).id.equals(id)) return list.get(index);
        return recursiveSearch(list, id, index + 1);
    }
    // Returns all registered borrowers
    public Collection<Borrower> getAllBorrowers() {
        return borrowerMap.values();
    }
}
