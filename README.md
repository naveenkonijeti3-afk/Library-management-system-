# Library Management System

A full-stack library app with two logins: **Student** and **Librarian**.

- **Frontend:** HTML, CSS, JavaScript
- **Backend:** Node.js + Express (REST API, JWT login)
- **SQL database (MySQL):** users, sections, books, borrowals, fines
- **NoSQL database (MongoDB):** activity log and student notifications (rewards and fines history)

## Features

**Student**
- Search books by title, author or ISBN, and filter by section
- Borrow books (up to 3 at a time, 14-day loan)
- Return on or before the due date: earn reward points
- Return late: fine per day overdue (must be paid before borrowing again)
- See points, unpaid fines, and rewards/fines history

**Librarian**
- Add or remove students (removal is blocked while they hold books)
- Add, edit copies of, or remove books
- Manage sections and see copies per section
- See all borrowals, overdue books and returns; accept returns and mark fines paid
- Dashboard with totals and a live activity feed

## Setup

You need Node.js 18+. For the databases, either use Docker (easiest) or install MySQL and MongoDB yourself.

```bash
# 1. Start MySQL and MongoDB
docker compose up -d

# 2. Install and configure the backend
cd backend
npm install
cp .env.example .env      # edit values if needed

# 3. Run
npm start
```

Open http://localhost:3000

The app creates the database, tables, default sections and the first librarian account automatically.

**Default librarian login:** `librarian@library.com` / `admin123` (change it in `.env` before first run).
The librarian creates student accounts from the Students tab.

## Library rules (edit in `backend/.env`)

| Setting | Default | Meaning |
|---|---|---|
| LOAN_DAYS | 14 | Days until a book is due |
| FINE_PER_DAY | 5 | Fine per late day (₹) |
| REWARD_POINTS | 10 | Points for an on-time return |
| MAX_ACTIVE_BORROWS | 3 | Books a student can hold |

## Project structure

```
backend/
  server.js            Express app entry
  config/db.js         MySQL + MongoDB connections, auto-setup
  sql/schema.sql       MySQL tables
  models/              MongoDB models (ActivityLog, Notification)
  middleware/auth.js   JWT + role checks
  routes/              auth, students, books, borrow, dashboard
frontend/
  index.html           Login (student / librarian)
  student.html         Student dashboard
  librarian.html       Librarian dashboard
  css/style.css, js/*.js
docker-compose.yml     MySQL + MongoDB for local dev
```

## API summary

| Method | Endpoint | Who |
|---|---|---|
| POST | /api/auth/login | all |
| GET/POST/DELETE | /api/students | librarian |
| GET | /api/books, /api/books/sections | all |
| POST/PUT/DELETE | /api/books, /api/books/sections | librarian |
| POST | /api/borrow | student |
| POST | /api/borrow/:id/return | student / librarian |
| POST | /api/borrow/:id/pay-fine | student / librarian |
| GET | /api/borrow/my | student |
| GET | /api/borrow?status=overdue | librarian |
| GET | /api/dashboard/stats, /logs | librarian |
| GET | /api/dashboard/me, /notifications | student |

## Upload to GitHub

```bash
git init
git add .
git commit -m "Library management system"
git branch -M main
git remote add origin https://github.com/<your-username>/<repo-name>.git
git push -u origin main
```

`.env` is in `.gitignore`, so your passwords are not uploaded.
