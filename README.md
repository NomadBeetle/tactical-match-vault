# Tactical Match Vault

**A Bring Your Own Project (BYOP) Capstone Submission for Java Programming**

## Overview

Tactical Match Vault is a local **Command Line Interface (CLI)** application designed for competitive tactical shooter players to **track match history, analyze performance, and securely store data offline**.

The project provides a clean and responsive way to:

* view complete match history
* calculate lifetime statistics such as **Win Rate** and **K/D Ratio**
* back up match data into a local **SQLite database**
* perform database backup asynchronously using **multithreading**

This project was developed as a capstone submission for the **Programming in Java Evaluated Course Project (BYOP)**.

---

## Problem Statement

Competitive players often want to review their past performance to identify patterns, improve consistency, and track long-term progress.

Most players either rely on scattered screenshots or third-party trackers.

Tactical Match Vault solves this problem by providing a lightweight offline system to:

* store match records
* compute useful statistics
* maintain persistent backups locally

---

## Academic Implementation

This project demonstrates the practical application of key Java course modules.

### Object-Oriented Programming (OOP)

The project follows OOP principles through modular class design.

Core classes:

* `Match` → data model for individual matches
* `FileParser` → CSV file ingestion
* `DatabaseManager` → JDBC and database operations
* `Main` → application control flow and menu logic

Features used:

* encapsulation
* constructors
* getters
* business logic methods
* method overriding (`toString()`)

---

### Exception Handling and I/O

Robust file handling is implemented using:

* `BufferedReader`
* `FileReader`
* `try-with-resources`
* `try-catch` blocks

This ensures graceful failure handling for:

* missing files
* corrupted CSV data
* invalid numeric input
* database errors

---

### Collections Framework

The project uses:

* `ArrayList<Match>`
* `List<Match>`

This enables dynamic storage, iteration, and processing of match history.

---

### Database Applications (JDBC)

Persistent storage is implemented using **SQLite JDBC**.

Key concepts used:

* `DriverManager`
* `Connection`
* `Statement`
* `PreparedStatement`
* SQL table creation
* safe insert queries

Database file:

```text
/data/match_vault.db
```

---

### Concurrency

The backup operation is executed on a **separate background thread**.

This keeps the CLI responsive while database operations run asynchronously.

Implemented using:

```java
Thread backupThread = new Thread(() -> {
    // database backup logic
});
```

---

## Features

* View all stored matches
* Compute lifetime statistics
* Calculate K/D ratio
* Calculate win percentage
* CSV data ingestion
* SQLite database backup
* asynchronous database write operations
* clean CLI menu system

---

## How to Run

### Prerequisites

* Java Development Kit (**JDK 8+**)
* SQLite JDBC driver inside `lib/`

Required driver:

```text
sqlite-jdbc-3.51.3.0.jar
```

---

## Compilation

Run from project root:

```bash
javac src/*.java
```

---

## Execution

### Windows (Command Prompt)

```bash
java -cp "src;lib/sqlite-jdbc-3.51.3.0.jar" Main
```

### Git Bash / Linux / macOS

```bash
java -cp "src:lib/sqlite-jdbc-3.51.3.0.jar" Main
```

---

## Project Structure

```text
TACTICAL-MATCH-VAULT/
│
├── data/
│   ├── matches.csv
│   └── match_vault.db
│
├── lib/
│   └── sqlite-jdbc-3.51.3.0.jar
│
├── src/
│   ├── Main.java
│   ├── Match.java
│   ├── FileParser.java
│   └── DatabaseManager.java
│
└── README.md
```

---

## Challenges Faced

A major challenge during development was:

* JVM classpath management
* SQLite driver integration
* terminal compatibility across Git Bash and Windows shell

These issues were resolved through:

* proper runtime classpath configuration
* robust error handling
* cross-platform execution commands

---

## Learning Outcomes

Through this project, I strengthened my understanding of:

* practical OOP design
* Java file handling
* exception management
* collections framework
* JDBC database integration
* concurrency using threads

This project helped bridge theoretical course concepts with a real-world use case.

---

## Developer

**Azaan Ahmad**
