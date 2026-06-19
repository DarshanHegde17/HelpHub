# HelpHub - Emergency Support Platform
## 🚀 Project Overview
HelpHub is a smart emergency support platform connecting blood donors, volunteers, hospitals, and emergency responders instantly during critical situations.

## 🎯 Features
- **Blood Donation System**: Connect with nearby blood donors instantly
- **Emergency Requests**: Quick ambulance and emergency medical support
- **Volunteer Network**: Community volunteers ready to help
- **Live Location Tracking**: Detect nearby hospitals and donors
- **User Authentication**: Secure login and registration system

- 

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 4.0.6
- **Language**: Java 17
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven

### Frontend
- **HTML5** with modern CSS3
- **Vanilla JavaScript** (No frameworks)
- **Responsive Design**
- **Animated UI** with Canvas API

## 📁 Project Structure
```
HelpHub/
├── src/
│   ├── main/
│   │   ├── java/com/helphub/helphub/
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java
│   │   │   │   ├── DonorController.java
│   │   │   │   └── EmergencyController.java
│   │   │   ├── model/
│   │   │   │   ├── User.java
│   │   │   │   ├── Donor.java
│   │   │   │   └── EmergencyRequest.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── DonorRepository.java
│   │   │   │   └── EmergencyRequestRepository.java
│   │   │   └── HelphubApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── index.html (Landing Page)
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── dashboard.html
│   │       │   ├── emergency.html
│   │       │   └── view-emergency.html
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 🔌 API Endpoints

### User Management
- `POST /api/users/register` - Register new user
- `POST /api/users/login` - User login

### Donor Management
- `POST /api/donors/add` - Add new donor
- `GET /api/donors/all` - Get all donors
- `GET /api/donors/search?bloodGroup={type}&city={city}` - Search donors

### Emergency Requests
- `POST /api/emergency/create` - Create emergency request
- `GET /api/emergency/all` - Get all emergency requests

## 🗄️ Database Configuration

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/helphub_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+

### Installation Steps

1. **Clone the repository**
```bash
git clone <repository-url>
cd HelpHub
```

2. **Create MySQL Database**
```sql
CREATE DATABASE helphub_db;
```

3. **Update Database Credentials**
Edit `src/main/resources/application.properties` with your MySQL credentials

4. **Build the Project**
```bash
mvnw clean install
```

5. **Run the Application**
```bash
mvnw spring-boot:run
```




6. **Access the Application**
- Landing Page: http://localhost:8080/index.html
- Login: http://localhost:8080/login.html
- Register: http://localhost:8080/register.html

## 🌐 Frontend Pages

### 1. Landing Page (index.html)
- Modern animated design with liquid wave background
- Hero section with typing animation
- Feature showcase cards
- Statistics section
- Inspirational quotes
- Contact/Feedback form
- Fully responsive

### 2. Login Page (login.html)
- User authentication
- Redirects to dashboard on success

### 3. Registration Page (register.html)
- New user registration
- Collects: name, email, password, phone, city

### 4. Dashboard (dashboard.html)
- User dashboard after login

### 5. Emergency Pages
- Create emergency requests
- View emergency requests

## 🎨 Design Features
- **Animated Background**: Canvas-based liquid wave animation
- **Typing Effect**: Dynamic headline with rotating text
- **Scroll Animations**: Reveal effects on scroll
- **3D Card Effects**: Interactive hover transformations
- **Responsive Design**: Mobile-first approach
- **Accessibility**: ARIA labels and semantic HTML

## 🔐 CORS Configuration
All controllers have `@CrossOrigin("*")` enabled for development. 
**Note**: Restrict this in production!

## 📝 API Usage Examples

### Register User
```javascript
fetch('http://localhost:8080/api/users/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'John Doe',
    email: 'john@example.com',
    password: 'password123',
    phone: '9876543210',
    city: 'Bengaluru'
  })
});
```

### Login User
```javascript
fetch('http://localhost:8080/api/users/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    email: 'john@example.com',
    password: 'password123'
  })
});
```

### Search Donors
```javascript
fetch('http://localhost:8080/api/donors/search?bloodGroup=O+&city=Bengaluru')
  .then(response => response.json())
  .then(data => console.log(data));
```

## 🐛 Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check database credentials in application.properties
- Ensure helphub_db database exists

### Port Already in Use
- Change port in application.properties: `server.port=8081`

### CORS Errors
- Ensure @CrossOrigin is present on controllers
- Check browser console for specific errors

## 📱 Browser Compatibility
- Chrome (recommended)
- Firefox
- Safari
- Edge

## 🤝 Contributing
This is an MCA Final Year Project. Contributions and suggestions are welcome!

## 📄 License
Educational Project - MCA Final Year

## 👥 Contact
- Email: support@helphub.com
- Phone: +91 9876543210
- Location: Bengaluru, Karnataka

---
**© 2026 HelpHub | MCA Final Year Project**
