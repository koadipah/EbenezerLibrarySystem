package service;

import model.Transaction;
import java.time.LocalDate;
import java.util.*;

public class OverdueMonitor {
    private PriorityQueue<Transaction> overdueQueue = new PriorityQueue<>(Comparator.comparing(t -> t.returnDate));

    public void add(Transaction t) {
        if (t.returnDate.isBefore(LocalDate.now().minusDays(14))) {
            overdueQueue.offer(t);
        }
    }

    public List<Transaction> getOverdue() {
        return new ArrayList<>(overdueQueue);
    }
}