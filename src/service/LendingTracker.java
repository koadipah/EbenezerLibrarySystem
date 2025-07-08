package service;

import model.Transaction;
import java.util.*;

public class LendingTracker {
    private Queue<Transaction> transactions = new LinkedList<>();

    public void borrow(Transaction t) {
        transactions.offer(t);
    }

    public void returnBook(Transaction t) {
        transactions.offer(t);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
