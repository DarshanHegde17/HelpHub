# HelpHub - Frontend-Backend Integration Guide

## 🔗 How Everything is Connected

### Architecture Overview
```
┌─────────────────────────────────────────────────────────────┐
│                     FRONTEND (HTML/CSS/JS)                  │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐  │
│  │ index.   │  │ login.   │  │register. │  │dashboard.│  │
│  │  html    │  │  html    │  │  html    │  │  html    │  │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘  └────┬─────┘  │
│       │             │              │             │         │
│       └─────────────┴──────────────┴─────────────┘         │
│                          │                                  │
│                    Fetch API Calls                          │
│                          │                                  │
└──────────────────────────┼──────────────────────────────────┘
                           │
                    HTTP Requests
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                  SPRING BOOT BACKEND                         │
│  ┌────────────────────────────────────────────────────────┐ │
│  │              REST Controllers (@RestController)        │ │
│  │  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐  │ │
│  │  │    User      │ │    Donor     │ │  Emergency   │  │ │
│  │  │  Controller  │ │  Controller  │ │  Controller  │  │ │
│  │  └──────┬───────┘ └──────┬───────┘ └──────┬───────┘  │ │
│  └─────────┼────────────────┼────────────────┼──────────┘ │
│            │                │                │             │
│  ┌─────────▼────────────────▼────────────────▼──────────┐ │
│  │              Service Layer (Business Logic)          │ │
│  └─────────┬────────────────┬────────────────┬──────────┘ │
│            │                │                │             │
│  ┌─────────▼────────────────▼────────────────▼──────────┐ │
│  │         Repository Layer (Spring Data JPA)           │ │
│  │  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ │ │
│  │  │    User      │ │    Donor     │ │  Emergency   │ │ │
│  │  │  Repository  │ │  Repository  │ │  Repository  │ │ │
│  │  └──────┬───────┘ └──────┬───────┘ └──────┬───────┘ │ │
│  └─────────┼────────────────┼────────────────┼──────────┘ │
└────────────┼────────────────┼────────────────┼────────────┘
             │                │                │
             └────────────────┴────────────────┘
                           │
                      JPA/Hibernate
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                    MySQL Database                            │
│  ┌──────────┐  ┌──────────┐  ┌──────────────────────┐     │
│  │   user   │  │  donor   │  │  emergency_request   │     │
│  └──────────┘  └──────────┘  └──────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

## 📄 Page-to-API Mapping

### 1. Landing Page (index.html)
**Purpose**: Marketing/Information page with animations

**Connections**:
- **Login Button** → Redirects to `login.html`
- **Request Help Button** → Redirects to `emergency.html`
- **Join Community Button** → Redirects to `register.html`
- **Feedback Form** → Currently client-side only (can be connected to backend)

**No Direct API Calls** - Pure frontend showcase

---

### 2. Registration Page (register.html)
**Purpose**: New user registration

**API Connection**:
```javascript
POST http://localhost:8080/api/users/register

Request Body:
{
  "name": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "city": "string"
}

Response: User object with generated ID
```

**Backend Handler**: `UserController.registerUser()`

**Flow**:
1. User fills form
2. JavaScript collects form data
3. Fetch API sends POST request
4. UserController receives request
5. UserRepository saves to database
6. Success message shown to user

---

### 3. Login Page (login.html)
**Purpose**: User authentication

**API Connection**:
```javascript
POST http://localhost:8080/api/users/login

Request Body:
{
  "email": "string",
  "password": "string"
}

Response: "Login Successful" or error message
```

**Backend Handler**: `UserController.loginUser()`

**Flow**:
1. User enters credentials
2. JavaScript sends POST request
3. UserController validates credentials
4. If valid → Redirect to `dashboard.html`
5. If invalid → Show error message

---

### 4. Dashboard (dashboard.html)
**Purpose**: User home after login

**Current State**: Basic welcome page

**Potential API Connections**:
- GET user profile data
- GET user's emergency requests
- GET nearby donors
- GET notifications

---

### 5. Emergency Page (emergency.html)
**Purpose**: Create emergency requests

**Expected API Connection**:
```javascript
POST http://localhost:8080/api/emergency/create

Request Body:
{
  "patientName": "string",
  "bloodGroup": "string",
  "hospital": "string",
  "city": "string",
  "phone": "string",
  "urgency": "string"
}

Response: EmergencyRequest object
```

**Backend Handler**: `EmergencyController.createRequest()`

---

### 6. View Emergency Page (view-emergency.html)
**Purpose**: Display all emergency requests

**Expected API Connection**:
```javascript
GET http://localhost:8080/api/emergency/all

Response: Array of EmergencyRequest objects
```

**Backend Handler**: `EmergencyController.getAllRequests()`

---

## 🔌 Complete API Reference

### User APIs

#### Register User
```http
POST /api/users/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "phone": "9876543210",
  "city": "Bengaluru"
}
```

#### Login User
```http
POST /api/users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

---

### Donor APIs

#### Add Donor
```http
POST /api/donors/add
Content-Type: application/json

{
  "name": "Jane Donor",
  "bloodGroup": "O+",
  "phone": "9876543210",
  "city": "Bengaluru",
  "available": true
}
```

#### Get All Donors
```http
GET /api/donors/all
```

#### Search Donors
```http
GET /api/donors/search?bloodGroup=O+&city=Bengaluru
```

---

### Emergency APIs

#### Create Emergency Request
```http
POST /api/emergency/create
Content-Type: application/json

{
  "patientName": "Emergency Patient",
  "bloodGroup": "O+",
  "hospital": "City Hospital",
  "city": "Bengaluru",
  "phone": "9876543210",
  "urgency": "HIGH"
}
```

#### Get All Emergency Requests
```http
GET /api/emergency/all
```

---

## 🎯 Data Flow Examples

### Example 1: User Registration Flow
```
1. User opens index.html
2. Clicks "Join Community" button
3. Redirected to register.html
4. Fills form: name, email, password, phone, city
5. Clicks "Register" button
6. JavaScript function registerUser() triggered
7. Fetch API sends POST to /api/users/register
8. Request hits UserController.registerUser()
9. User object created with role="USER"
10. UserRepository.save() persists to MySQL
11. Response sent back to frontend
12. Success alert shown
13. User can now login
```

### Example 2: Login Flow
```
1. User opens login.html
2. Enters email and password
3. Clicks "Login" button
4. JavaScript function loginUser() triggered
5. Fetch API sends POST to /api/users/login
6. Request hits UserController.loginUser()
7. UserRepository.findByEmail() queries database
8. Password compared
9. If match: "Login Successful" returned
10. Frontend redirects to dashboard.html
11. If no match: Error message shown
```

### Example 3: Emergency Request Flow
```
1. User clicks "Request Help" on index.html
2. Redirected to emergency.html
3. Fills emergency form
4. Submits form
5. Fetch API sends POST to /api/emergency/create
6. EmergencyController.createRequest() receives data
7. EmergencyRequest object created
8. EmergencyRequestRepository.save() persists to MySQL
9. Response sent back
10. Confirmation shown to user
11. Emergency request now visible in /api/emergency/all
```

---

## 🔐 CORS Configuration

All controllers have CORS enabled:
```java
@CrossOrigin("*")
```

This allows frontend (running on any origin) to call backend APIs.

**Production Note**: Change `"*"` to specific frontend URL:
```java
@CrossOrigin("https://helphub.com")
```

---

## 🗄️ Database Schema

### User Table
```sql
CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  phone VARCHAR(20),
  city VARCHAR(100),
  role VARCHAR(50) DEFAULT 'USER'
);
```

### Donor Table
```sql
CREATE TABLE donor (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  blood_group VARCHAR(10),
  phone VARCHAR(20),
  city VARCHAR(100),
  available BOOLEAN DEFAULT TRUE
);
```

### Emergency Request Table
```sql
CREATE TABLE emergency_request (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  patient_name VARCHAR(255),
  blood_group VARCHAR(10),
  hospital VARCHAR(255),
  city VARCHAR(100),
  phone VARCHAR(20),
  urgency VARCHAR(50),
  status VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🚀 Testing Integration

### Test 1: Complete User Journey
```javascript
// 1. Register
await fetch('http://localhost:8080/api/users/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Test User',
    email: 'test@test.com',
    password: 'test123',
    phone: '9999999999',
    city: 'Bengaluru'
  })
});

// 2. Login
const loginResponse = await fetch('http://localhost:8080/api/users/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    email: 'test@test.com',
    password: 'test123'
  })
});
console.log(await loginResponse.text()); // "Login Successful"
```

### Test 2: Donor Management
```javascript
// Add donor
await fetch('http://localhost:8080/api/donors/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Donor Name',
    bloodGroup: 'O+',
    phone: '9999999999',
    city: 'Bengaluru',
    available: true
  })
});

// Search donors
const donors = await fetch('http://localhost:8080/api/donors/search?bloodGroup=O+&city=Bengaluru')
  .then(r => r.json());
console.log(donors);
```

---

## 📱 Frontend-Backend Communication

### Fetch API Pattern Used
```javascript
async function callAPI(url, method, data) {
  const options = {
    method: method,
    headers: {
      'Content-Type': 'application/json'
    }
  };
  
  if (data) {
    options.body = JSON.stringify(data);
  }
  
  const response = await fetch(url, options);
  return response;
}
```

### Error Handling Pattern
```javascript
try {
  const response = await fetch(url, options);
  if (response.ok) {
    const data = await response.json();
    // Handle success
  } else {
    // Handle HTTP errors
    console.error('HTTP Error:', response.status);
  }
} catch (error) {
  // Handle network errors
  console.error('Network Error:', error);
}
```

---

## ✅ Integration Checklist

- [x] Backend Spring Boot application configured
- [x] MySQL database setup
- [x] REST Controllers created with @CrossOrigin
- [x] JPA Repositories configured
- [x] Frontend HTML pages created
- [x] Landing page with animations
- [x] Login page with API integration
- [x] Registration page with API integration
- [x] Dashboard page created
- [x] Emergency pages created
- [x] Fetch API calls implemented
- [x] Error handling in place
- [x] Responsive design implemented

---

## 🎯 Next Integration Steps

1. **Session Management**: Add JWT tokens for authentication
2. **Real-time Updates**: WebSocket for live notifications
3. **File Upload**: Profile pictures, documents
4. **Email Integration**: Send alerts to donors
5. **Maps Integration**: Google Maps for location
6. **Payment Gateway**: For donations
7. **Admin Panel**: Manage all entities
8. **Analytics Dashboard**: Statistics and reports

---

**Integration Complete! 🎉**
