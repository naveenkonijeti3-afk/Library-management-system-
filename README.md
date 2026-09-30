# Library Management System

A complete starter full-stack Library Management System.

## Stack
- Frontend: HTML, CSS, JavaScript
- Backend: Java 17 + Spring Boot + Spring Data JPA
- SQL database: MySQL
- NoSQL database: MongoDB (activity/reward logs)
- Authentication: simple role-based login (STUDENT / LIBRARIAN)

## Features
### Student
- Login
- View available books
- Borrow a book
- Return a book
- On-time return reward points
- Late return fine
- View borrowing history and reward/fine information

### Librarian
- Login
- Add/remove students
- Add/remove books
- View books section-wise
- View active borrowals and returned submissions
- View dashboard statistics

## Run
1. Install Java 17+, Maven, MySQL and MongoDB.
2. Create a MySQL database using `backend/src/main/resources/schema.sql`.
3. Edit MySQL/MongoDB settings in `backend/src/main/resources/application.properties`.
4. Run backend:
   `cd backend`
   `mvn spring-boot:run`
5. Open `frontend/index.html` in a browser, or serve the frontend with a simple local server.

Default demo users are created automatically:
- Student: student1 / student123
- Librarian: librarian1 / librarian123

This project is intentionally easy to understand and extend. For production, replace the demo password handling with BCrypt/JWT/Spring Security.
