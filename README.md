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

## 🧠 System Design / Architecture Flow

### 🔗 URL Shortening Flow
1. User submits a long URL via frontend
2. Backend Controller receives the request
3. Service layer generates a short URL using Base62 encoding
4. Short URL mapping is stored in PostgreSQL
5. Redis is checked first for existing URL mappings (cache-aside strategy)
6. If not found in cache, database is queried and result is cached in Redis
7. Response returns short URL to the user

---

### 🔁 Redirection Flow
1. User accesses short URL
2. Backend checks Redis cache for original URL
3. If cache miss → fetch from PostgreSQL
4. Redirect user to original URL
5. Cache is updated for faster future access

---

### 📊 Analytics & Event Processing Flow
1. Every redirect event is published to Kafka
2. Kafka consumer processes click events asynchronously
3. Analytics data is stored in PostgreSQL
4. Logs are generated for monitoring and debugging

---

### 🚦 Rate Limiting Flow
1. Each incoming request is checked against rate limiter
2. If threshold exceeded → request is rejected
3. Ensures system stability and prevents abuse
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

