# 📚 Personal Library API

A RESTful API built with Spring Boot for managing a personal book library. Users can securely authenticate, manage their book collection, and perform CRUD operations.

## 🚀 Features

- User registration and login
- JWT Authentication
- Secure endpoints with Spring Security
- Create, read, update and delete books
- SQLite database
- RESTful API architecture

## 🛠️ Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- SQLite
- Maven

## 📁 Project Structure

```
src
├── controller
├── model
├── repository
├── security
├── config
└── PersonalLibraryApiApplication
```

## ⚙️ Getting Started

### Clone the repository

```bash
git clone https://github.com/yusuftepe/personal-library-api.git
```

### Navigate to the project

```bash
cd personal-library-api
```

### Run the application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8080
```

## 🔑 Authentication

This project uses JWT (JSON Web Token) authentication.

1. Register a new account.
2. Log in to receive a JWT token.
3. Include the token in the Authorization header:

```
Authorization: Bearer <your_token>
```

## 📌 Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Login |
| GET | /books | Get all books |
| GET | /books/{id} | Get book by ID |
| POST | /books | Add a new book |
| PUT | /books/{id} | Update a book |
| DELETE | /books/{id} | Delete a book |

> Update endpoint names if they are different in your project.

## 🔮 Future Improvements

- DTO implementation
- Request validation
- Global exception handling
- Swagger/OpenAPI documentation
- Unit and integration tests
- Docker support

## 👤 Author

**Yusuf Tepe**

GitHub: https://github.com/yusuftepe