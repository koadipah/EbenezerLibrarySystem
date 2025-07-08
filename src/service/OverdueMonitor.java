package service;

import model.Transaction;
import model.Borrower;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class OverdueMonitor {
    private PriorityQueue<Transaction> overdueQueue = new PriorityQueue<>(Comparator.comparing(t -> t.returnDate));
    private BorrowerRegistry registry;

    public OverdueMonitor(BorrowerRegistry registry) {
        this.registry = registry;
    }

    public void add(Transaction t) {
        // Check if overdue by 14+ days
        long daysLate = ChronoUnit.DAYS.between(t.returnDate, LocalDate.now());
        if (daysLate > 14) {
            overdueQueue.offer(t);
            Borrower b = registry.getBorrowerById(t.borrowerId);
            if (b != null) {
                b.fines += (daysLate * 1.0); // $1 per day late
            }
        }
    }

    public List<Transaction> getOverdue() {
        if (overdueQueue.isEmpty()) {
            System.out.println("No overdue books at the moment.");
            return Collections.emptyList();
        }
        System.out.println("Overdue books:");
        System.out.println(" ");
        return new ArrayList<>(overdueQueue);
    }
}
