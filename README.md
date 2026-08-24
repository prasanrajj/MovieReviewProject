# Movie Search and Review Application

A full-stack application for searching movies, viewing movie details, and managing movie reviews.

## Technologies

* **Frontend:** React, JavaScript, CSS, Vite
* **Backend:** Java, Spring Boot, REST API, Spring Data JPA
* **Database:** MySQL
* **External API:** OMDb API

## Features

* Search movies
* View movie details
* Add reviews
* View reviews
* Update reviews
* Delete reviews
* Rate movies from 1–5
* Review validation
* review sentiment analysis

## Application Flow

```text
React Frontend
      ↓
Spring Boot Backend
      ↓
MySQL Database
```

Movie information is retrieved from the OMDb API.

## How to Run  

Environment Setup
Create a .env file in the root of the frontend folder, at the same level as package.json.
Add your OMDb API key:
VITE_OMDB_API_KEY=your_api_key_here
Replace your_api_key_here with your actual OMDb API key.

### 1. Start MySQL

Make sure MySQL is running and the required database is available.

### 2. Start Backend

Open the backend project in your IDE and run the Spring Boot application.

Backend:

```text
http://localhost:8080
```

### 3. Start Frontend

Open the frontend project and run:

```bash
npm install
npm run dev
```

Frontend:

```text
http://localhost:5173
```

### 4. Open the Application

Open the frontend URL in your browser:

```text
http://localhost:5173
```

The application is now ready to use.
