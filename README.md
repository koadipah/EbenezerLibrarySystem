# Ebenezer Library Management System

A fully offline, file-based Java console application for managing book inventory, borrowers, lending, and overdue tracking for the Ebenezer Community Library in Ashaiman.

---

## 📁 Project Structure

```
src/
├── Main.java                     # Entry point
├── model/                       # POJO classes
│   ├── Book.java
│   ├── Borrower.java
│   └── Transaction.java
├── service/                     # Core business logic
│   ├── LibrarySystem.java       # App runner and command loop
│   ├── BookInventory.java
│   ├── BorrowerRegistry.java
│   ├── LendingTracker.java
│   └── OverdueMonitor.java
├── utils/                       # File IO and helpers
│   ├── FileManager.java         # Save/load books, borrowers, transactions
│   ├── Sorter.java              # Merge sort for book titles
│   └── Searcher.java            # Linear search by ISBN
```

---

## 🚀 How to Run

1. Compile the project:
```bash
javac src/**/*.java
```

2. Run the main class:
```bash
java -cp src App
```

---

## ✅ Features Implemented

### 1. 📚 Book Inventory
- Add, remove, and list books.
- Store: title, author, ISBN, category, year, publisher, shelf.
- Categorized via `HashMap<String, List<Book>>`.

### 2. 🔍 Search & Sort
- Linear search by ISBN.
- Merge sort by title.

### 3. 👥 Borrower Registry
- Store borrower info in `HashMap<String, Borrower>`.
- Recursive search by ID.

### 4. 📦 Lending Tracker
- Transactions via `Queue<Transaction>`.
- Records borrowing and returning.

### 5. ⏰ Overdue Monitor
- Tracks overdue returns using `PriorityQueue` (min-heap).
- Flags overdue (> 14 days) and adds fines.

### 6. 🗂️ File Logging
- Auto-load from `books.txt`, `borrowers.txt`, and `transactions.txt`.
- Saves to files on request or exit.

### 7. 📊 Reports
- Most borrowed books.
- Borrowers with highest fines.
- Inventory count by category.

---

## 📈 Performance Overview

| Algorithm             | Where Used                 | Time Complexity         |
|----------------------|----------------------------|-------------------------|
| Merge Sort           | Sort book titles           | O(n log n), stable      |
| Linear Search        | ISBN lookups               | O(n) worst, Ω(1) best   |
| Priority Queue (heap)| Overdue books              | O(log n) per insert     |
| Recursion            | Borrower ID search         | O(n)                    |

---

## 🧪 Next Steps (Optional)
- Add unit tests using JUnit.
- Enhance file validation/error handling.
- Migrate to GUI (Swing/JavaFX) or web interface.

---

## 📌 Notes
- No external libraries used.
- Designed to run offline with full local persistence.
- Extendable to other formats (e.g., CSV, JSON, SQLite) if needed.

---

© 2025 - Developed for Ebenezer Community Library
