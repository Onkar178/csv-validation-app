# csv-validation-app
CSV Upload &amp; Validation App using Spring Boot and React. Supports file upload, date validation (yyyy-MM-dd), and row-wise error reporting.


# CSV Upload & Validation App

##  Features
- Upload CSV file
- Validate Name and DateOfBirth
- Date format: yyyy-MM-dd
- Row-wise error reporting

## Tech Stack
- Java, Spring Boot (Backend)
- React, Axios (Frontend)

##  How to Run

### Backend
1. Open backend project
2. Run Spring Boot application
3. Server runs on http://localhost:8080

### Frontend
1. Open frontend folder
2. Run:
   npm install
   npm run dev
3. Open http://localhost:5173

## 📡 API Endpoint
POST /api/upload

## 📄 Sample File
Use sample_users.csv for testing

## ✅ Validation Rules
- Name must not be empty
- DateOfBirth must not be empty
- Date format must be yyyy-mm-dd
