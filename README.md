# Library Management System

This is a RESTful API for a simple library management system. The API allows users to perform various actions such as registering books, borrowers, and managing book borrowing and returning.

## Table of Contents

- [Project Overview](#project-overview)
- [Data Models](#data-models)
- [API Endpoints](#api-endpoints)
- [Setup and Running the Project](#setup-and-running-the-project)

## Project Overview

The project is built using Java 17 and the Spring Boot framework. It includes the following features:
- Register new books and borrowers.
- Borrow and return books.
- Get a list of all books, including filters for borrowed and available books.
- Get a list of all borrowers.
- Authentication for API users.

## Data Models

The project consists of four main tables:

1. **Users**: Stores user details for authentication.
2. **Books**: Stores book details.
3. **Borrowers**: Stores borrower details.
4. **Book_Borrower**: Tracks which borrower has borrowed which book.

### Users Table
- `id`: Unique identifier for the user.
- `email`: Email address of the user.
- `password`: Password of the user.

### Books Table
- `id`: Unique identifier for the book.
- `isbn`: ISBN number of the book.
- `title`: Title of the book.
- `author`: Author of the book.
- `is_borrowed`: Boolean indicating if the book is currently borrowed.

### Borrowers Table
- `id`: Unique identifier for the borrower.
- `name`: Name of the borrower.
- `email`: Email address of the borrower.

### Book_Borrower Table
- `id`: Unique identifier for the book-borrower relationship.
- `book_id`: ID of the borrowed book.
- `borrower_id`: ID of the borrower.
- `borrowed_date`: Date when the book was borrowed.
- `returned_date`: Date when the book was returned (nullable).

## API Endpoints

### Authentication Endpoint
- **POST /login**: Authenticates a user and returns a JWT token.
### Book Endpoints
- **POST /api/v1/book**: Registers a new book.
- **GET /api/v1/book**: Retrieves all books, with an optional filter for borrowed books.
### Borrower Endpoints
- **POST /api/v1/borrower**: Registers a new borrower.
- **GET /api/v1/borrower**: Retrieves all borrowers.
### Book Borrower Endpoints
- **POST /api/v1/book/borrow**: Borrows a book.
- **PUT /api/v1/book/return**: Returns a borrowed book.
- **GET /api/v1/book/borrowed-books**: Retrieves a list of all borrowed books. 
## Setup and Running the Project

### Prerequisites
- Clone the repository:https://github.com/RaheemBux/library-management-system.git
- Java 17
- Maven
- MySQL
- Docker (for containerization)
