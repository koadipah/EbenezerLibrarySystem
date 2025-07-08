package service;

import model.Borrower;
import java.util.*;

public class BorrowerRegistry {
    private Map<String, Borrower> borrowerMap = new HashMap<>();

    public void addBorrower(Borrower borrower) {
        borrowerMap.put(borrower.id, borrower);
    }

    public Borrower getBorrowerById(String id) {
        return recursiveSearch(new ArrayList<>(borrowerMap.values()), id, 0);
    }

    private Borrower recursiveSearch(List<Borrower> list, String id, int index) {
        if (index >= list.size()) return null;
        if (list.get(index).id.equals(id)) return list.get(index);
        return recursiveSearch(list, id, index + 1);
    }

    public Collection<Borrower> getAllBorrowers() {
        return borrowerMap.values();
    }
}
