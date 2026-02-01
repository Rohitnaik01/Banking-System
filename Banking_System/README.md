# 🏦 Banking Management System (Java + MySQL)

A console-based Banking Management System built using Java and MySQL that allows users to register, log in, create bank accounts, manage balances, and perform secure fund transfers while practicing real-world backend development concepts.

---

## 📌 Problem Statement

Managing banking operations such as user authentication, account creation, balance handling, and secure transactions requires a structured and reliable system. This project simulates a basic banking environment to understand how these operations are implemented using Java, JDBC, and a relational database.

---

## ✅ Solution

This application provides a complete banking workflow where users can sign up, log in, create multiple bank accounts, deposit money, check balances, and transfer funds securely. Java is used for business logic, JDBC for database connectivity, and MySQL for persistent data storage.

---

## ✨ Features

- User registration and login  
- Create multiple bank accounts per user  
- Auto-generated secure account numbers  
- Deposit money with security PIN validation  
- Balance enquiry  
- Fund transfer between bank accounts  
- Delete user account  
- Transaction safety using commit and rollback  

---

## 🛠 Tech Stack

- **Language:** Java  
- **Database:** MySQL  
- **Connectivity:** JDBC  
- **Concepts:** OOP, Packages, Exception Handling, Prepared Statements, Transaction Management  
- **Tools:** Git, GitHub, MySQL, Eclipse / IntelliJ / VS Code  

---

## 📂 Project Structure

Banking_System/
└── src/
├── Models/
│ ├── Accounts.java
│ └── Users.java
└── Services/
├── AccountManager.java
├── UserManager.java
└── Main.java


---

## 🧠 Module Overview

- **Users (Model):** Stores user details like name, email, mobile number, and password  
- **Accounts (Model):** Manages bank account details such as account number, security PIN, and balance  
- **UserManager (Service):** Handles user signup, login, validation, and account deletion  
- **AccountManager (Service):** Manages account creation, deposits, balance checks, and fund transfers  
- **Main:** Application entry point and menu-driven flow controller  

---

## ▶️ How to Run the Project

### 1️⃣ Database Setup

Create a MySQL database named `bankdb` and execute:

```sql
CREATE TABLE users (
  email VARCHAR(50) PRIMARY KEY,
  name VARCHAR(50),
  mobile VARCHAR(15),
  password VARCHAR(50)
);

CREATE TABLE accounts (
  account_no VARCHAR(20) PRIMARY KEY,
  holder_name VARCHAR(50),
  holder_email VARCHAR(50),
  security_pin INT,
  balance DOUBLE DEFAULT 0
);

2️⃣ Update Database Credentials

Update the following in Main.java:

static private final String url = "jdbc:mysql://127.0.0.1:3306/bankdb";
static private final String username = "root";
static private final String password = "your_password";

3️⃣ Run the Application

Open the project in a Java IDE

Run Main.java

Follow the console instructions

📘 What I Learned

Implementing real-world banking logic using Java

Connecting Java applications with MySQL using JDBC

Using prepared statements to improve security

Managing database transactions with commit and rollback

Structuring backend code using models and services

🚀 Future Improvements

Password encryption

Transaction history feature

Improved validations and error handling

Web-based version using Spring Boot

👤 Author

Naik Rohit Ramesh
GitHub: https://github.com/Rohitnaik01
