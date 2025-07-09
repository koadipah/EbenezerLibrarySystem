package service;

import java.util.*;
import model.Transaction;

public class LendingTracker {
     // Queue is used to maintain the chronological order of transactions (FIFO behavior)
    private Queue<Transaction> transactions = new LinkedList<>();

    // Adds a borrow transaction to the queue
    public void borrow(Transaction t) {
        transactions.offer(t);
    }
    
     // Adds a return transaction to the queue
    public void returnBook(Transaction t) {
        transactions.offer(t);
    }
    // Returns all transactions as a list for processing or reporting
    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
