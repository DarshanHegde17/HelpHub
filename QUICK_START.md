# HelpHub - Quick Start Guide

## ⚡ Quick Setup (5 Minutes)

### Step 1: Database Setup
```sql
-- Open MySQL Command Line or Workbench
CREATE DATABASE helphub_db;
```

### Step 2: Configure Database
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 3: Run the Application
```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Step 4: Access the Application
Open your browser and visit:
- **Landing Page**: http://localhost:8080/index.html
- **Login**: http://localhost:8080/login.html
- **Register**: http://localhost:8080/register.html

## 🎯 Testing the Application

### 1. Register a New User
1. Go to http://localhost:8080/index.html
2. Click "Join Community" button
3. Fill in the registration form
4. Click "Register"

### 2. Login
1. Click "Login" button in navbar
2. Enter your email and password
3. Click "Login"
4. You'll be redirected to dashboard

### 3. Test API Endpoints

#### Using Browser Console
```javascript
// Register User
fetch('http://localhost:8080/api/users/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Test User',
    email: 'test@example.com',
    password: 'test123',
    phone: '9876543210',
    city: 'Bengaluru'
  })
}).then(r => r.json()).then(console.log);

// Login User
fetch('http://localhost:8080/api/users/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    email: 'test@example.com',
    password: 'test123'
  })
}).then(r => r.text()).then(console.log);

// Add Donor
fetch('http://localhost:8080/api/donors/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'John Donor',
    bloodGroup: 'O+',
    phone: '9876543210',
    city: 'Bengaluru',
    available: true
  })
}).then(r => r.json()).then(console.log);

// Get All Donors
fetch('http://localhost:8080/api/donors/all')
  .then(r => r.json())
  .then(console.log);

// Search Donors
fetch('http://localhost:8080/api/donors/search?bloodGroup=O+&city=Bengaluru')
  .then(r => r.json())
  .then(console.log);

// Create Emergency Request
fetch('http://localhost:8080/api/emergency/create', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    patientName: 'Emergency Patient',
    bloodGroup: 'O+',
    hospital: 'City Hospital',
    city: 'Bengaluru',
    phone: '9876543210',
    urgency: 'HIGH'
  })
}).then(r => r.json()).then(console.log);

// Get All Emergency Requests
fetch('http://localhost:8080/api/emergency/all')
  .then(r => r.json())
  .then(console.log);
```

## 🔍 Verify Everything is Working

### Check Backend
1. Open terminal and run the application
2. Look for: `Started HelphubApplication in X seconds`
3. No errors should appear

### Check Frontend
1. Open http://localhost:8080/index.html
2. You should see:
   - Animated heartbeat preloader (3 seconds)
   - Liquid wave background animation
   - Typing effect in hero section
   - All images loading properly
   - Smooth scroll animations

### Check Database
```sql
-- Verify tables were created
USE helphub_db;
SHOW TABLES;

-- Should show:
-- user
-- donor
-- emergency_request
```

## 🎨 Landing Page Features

### Navigation
- **Home**: Scrolls to hero section
- **Features**: Scrolls to features section
- **Quotes**: Scrolls to quotes section
- **Contact**: Scrolls to footer contact form
- **Login Button**: Redirects to login.html

### Hero Section Buttons
- **Request Help**: Goes to emergency.html
- **Join Community**: Goes to register.html

### Interactive Elements
- Animated liquid wave background (responds to mouse movement)
- Typing animation with rotating text
- 3D card hover effects
- Scroll reveal animations
- Feature cards with scan line effects
- Feedback form in footer

## 📊 Database Tables Structure

### User Table
- id (Primary Key)
- name
- email (Unique)
- password
- phone
- city
- role (Default: USER)

### Donor Table
- id (Primary Key)
- name
- bloodGroup
- phone
- city
- available (Boolean)

### Emergency Request Table
- id (Primary Key)
- patientName
- bloodGroup
- hospital
- city
- phone
- urgency
- status
- createdAt

## 🚨 Common Issues & Solutions

### Issue: Port 8080 already in use
**Solution**: Change port in application.properties
```properties
server.port=8081
```

### Issue: Database connection failed
**Solution**: 
1. Check MySQL is running
2. Verify database name: `helphub_db`
3. Check username/password in application.properties

### Issue: White screen on frontend
**Solution**:
1. Check browser console for errors
2. Verify backend is running
3. Clear browser cache

### Issue: CORS errors
**Solution**: Controllers already have @CrossOrigin("*")
If still issues, check browser console for specific error

### Issue: Images not loading
**Solution**: Images use Unsplash CDN - check internet connection

## 🎯 Next Steps

1. **Enhance Dashboard**: Add user profile, emergency history
2. **Add Maps Integration**: Google Maps for location tracking
3. **Implement Real-time Notifications**: WebSocket for instant alerts
4. **Add Blood Bank Management**: Track blood inventory
5. **Mobile App**: React Native or Flutter version
6. **Admin Panel**: Manage users, donors, emergencies
7. **Email Notifications**: Send alerts to donors
8. **SMS Integration**: Twilio for emergency SMS

## 📞 Need Help?

- Check console logs for errors
- Verify all dependencies are installed
- Ensure Java 17 is installed: `java -version`
- Ensure Maven is working: `mvn -version`
- Check MySQL connection: `mysql -u root -p`

---
**Happy Coding! 🚀**
