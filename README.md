# DevTrack — Java Full-Stack Career Platform

A portfolio-ready starter application for tracking job applications. Built with Java 17, Spring Boot, Spring Security + JWT, Spring Data JPA, MySQL, React, Vite, and Axios.

## Features included
- Register and log in with email/password (passwords are BCrypt-hashed)
- JWT-protected REST API
- Create, view, update, and delete job applications
- Track company, role, location, application date, status, job URL, and notes
- Dashboard statistics by application status
- Search and filter applications in the UI
- Responsive interface

## Prerequisites
- JDK 17 or newer
- Maven 3.9+ (or use your IDE's Maven integration)
- Node.js 20+ and npm
- MySQL 8+

## 1. Create the database
Run this in MySQL:
```sql
CREATE DATABASE devtrack_db;
```

## 2. Configure the backend
Open `backend/src/main/resources/application.properties`. Set your MySQL username/password. The default assumes local MySQL username `root` and password `root`; change these to match your machine. You can also set `DB_USERNAME` and `DB_PASSWORD` environment variables.

From the `backend` directory, run:
```bash
mvn spring-boot:run
```
Backend URL: `http://localhost:8080`

## 3. Run the frontend
In a second terminal:
```bash
cd frontend
npm install
npm run dev
```
Open the URL printed by Vite (normally `http://localhost:5173`).

## API overview
| Method | Endpoint | Auth | Purpose |
|---|---|---|---|
| POST | `/api/auth/register` | No | Create account |
| POST | `/api/auth/login` | No | Log in and receive JWT |
| GET | `/api/jobs` | Yes | List your jobs |
| POST | `/api/jobs` | Yes | Create a job application |
| GET | `/api/jobs/{id}` | Yes | Read one of your applications |
| PUT | `/api/jobs/{id}` | Yes | Update one of your applications |
| DELETE | `/api/jobs/{id}` | Yes | Delete one of your applications |
| GET | `/api/dashboard/stats` | Yes | Counts by status and total |

Protected API requests use `Authorization: Bearer <token>`.

## Job status values
`APPLIED`, `ASSESSMENT`, `INTERVIEW`, `OFFER`, `REJECTED`, `WITHDRAWN`

## Important notes
- This is a functional starter/MVP, not a production-hardened service. Before production, add refresh-token/session strategy, rate limiting, email verification, stronger validation/observability, migrations (Flyway/Liquibase), and secrets management.
- JWT signing secret is configurable using `JWT_SECRET`. Change it to a long random secret outside local development.
- The UI uses a local development API base URL in `frontend/src/api.js`.
- AI resume analysis and AI interview generation are not implemented in this initial ZIP; they are good next milestones after the core app is running.

## Suggested next steps
1. Run the backend and verify register/login using Postman.
2. Run the React frontend and create a few sample applications.
3. Add resume PDF upload + text extraction.
4. Add AI-generated interview questions using an API provider.
5. Deploy the backend and frontend, and configure production CORS and secrets.
