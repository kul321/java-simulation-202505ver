# 📚 BookStore Inventory System

A console-based bookstore management system built in Java.  
This project allows users to manage book records, search inventory, and simulate sales logic through a simple menu-based interface.

## 📌 Features

- Register new books (title, author, price, quantity)
- View all books in inventory
- Search for books by title
- Delete books from inventory
- Simple sales simulation (stock decreases on sale)

## ▶️ How to Run

1. Compile all `.java` files:
   ```bash
   javac *.java
   ```

2. Run the main program:
   ```bash
   java BookMainTest
   ```

## 📁 Files Overview

| File | Description |
|------|-------------|
| `Main.java` | Initial entry point (may delegate to `BookMainTest`) |
| `BookMainTest.java` | Main menu and user interaction logic |
| `Book.java` | Represents a book (title, author, price, quantity) |
| `BookRepository.java` | Manages book list and provides add/search/delete logic |

## 💡 Purpose

This project was created to practice:
- Object-oriented modeling using classes and methods
- List-based data handling
- Basic CRUD operations
- Console input/output and menu-driven interfaces

---

> 👤 GitHub: [kul321](https://github.com/kul321)
