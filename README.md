# 🔗 URL Shortener App

A full-stack URL Shortener application built using **Spring Boot (Java)** for the backend and **Angular** for the frontend.

The backend handles URL generation, authentication, caching, analytics, and request control.  
The frontend is an Angular-based UI that includes a URL generation page where users can paste a long URL, generate a short URL using backend APIs, copy the generated link, and view the history of URLs created during the session.

---

## 🚀 Features

### 🔐 Authentication
- User Signup and Login
- JWT-based authentication (handled in backend)

### 🔗 URL Shortening
- Generate short URLs from long URLs
- Redirect to original URL using short links

### ⚡ Performance Improvements
- Redis caching for faster URL lookup

### 📊 Backend Analytics
- Click tracking for shortened URLs
- Stored and processed in backend

### 🚦 Rate Limiting
- Prevents excessive API requests

### 📡 Kafka Integration
- Used to process URL-related events asynchronously
- Handles background processing for click tracking and analytics updates

### 🗄️ Database Layer
- PostgreSQL is used for persistent storage
- Stores:
    - User data
    - URL mappings
    - Analytics data

---

## 🔄 Request Flow

1. User sends request to backend (signup/login/shorten URL)
2. Controller receives request
3. Service layer processes business logic
4. Redis is checked for cached URLs
5. PostgreSQL is used if data is not in cache
6. Kafka handles background processing (analytics/events)
7. Response is returned to client

---

## 🛠️ Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- PostgreSQL
- Redis
- Kafka

### Frontend
- Angular
- TypeScript
- HTML
- CSS

---

## 👨‍💻 Author

**Deepali Lanjewar**
