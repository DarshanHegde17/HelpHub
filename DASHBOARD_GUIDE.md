# HelpHub Dashboard - User Guide

## 🎯 Dashboard Overview

The dashboard has the same beautiful design as the landing page with animated liquid wave background and interactive cards.

## 📋 Main Features

### 1. Blood Donation Card 🩸
**Purpose**: Register yourself as a blood donor

**How to Use**:
1. Click on the "Blood Donation" card
2. A popup modal will appear
3. Fill in the registration form:
   - **Full Name** (required)
   - **Email** (required)
   - **Phone Number** (required)
   - **Gender** (Male/Female/Other) (required)
   - **Age** (18-65 years) (required)
   - **Blood Group** (A+, A-, B+, B-, AB+, AB-, O+, O-) (required)
   - **City** (required)
   - **Medical Conditions** (optional - any diseases or health issues)
4. Click "Register as Donor" button
5. Success message will appear
6. Your details will be added to the Blood Alerts section

---

### 2. Blood Alerts Card 📋
**Purpose**: View all registered blood donors

**What You'll See**:
- List of all registered donors
- Each donor card shows:
  - **Name** with avatar (first letter)
  - **Blood Group** badge
  - **Email** address
  - **Phone Number**
  - **Age**
  - **Gender**
  - **City/Location**
  - **Medical Conditions** (if any)
  - **Availability Status**

**How to Use**:
1. Click on "Blood Alerts" card
2. Automatically scrolls to donors section
3. Browse through all registered donors
4. Contact them directly using phone/email

---

### 3. Emergency Request Card 🚑
**Purpose**: Create urgent blood requirement request

**How to Use**:
1. Click on "Emergency Request" card
2. Redirects to emergency.html
3. Fill emergency form
4. Submit request

---

### 4. View Emergencies Card 📢
**Purpose**: See all active emergency requests

**How to Use**:
1. Click on "View Emergencies" card
2. Redirects to view-emergency.html
3. See all emergency blood requests
4. Respond to help those in need

---

## 🎨 Design Features

### Visual Elements
- **Animated Background**: Liquid wave with interactive particles
- **Hover Effects**: Cards lift up on hover with glow
- **Smooth Animations**: Fade-in and slide-up effects
- **Responsive Design**: Works on all screen sizes
- **Modern UI**: Glass-morphism and gradient effects

### Color Scheme
- **Primary**: Red (#ff4d4d) - Emergency/Blood
- **Secondary**: Blue (#4da6ff) - Support/Trust
- **Background**: Dark gradient with animated particles
- **Text**: White with various opacity levels

---

## 📊 Donor Registration Form Details

### Required Fields
```
Name:        Full name of the donor
Email:       Contact email address
Phone:       10-digit phone number
Gender:      Male / Female / Other
Age:         Between 18-65 years
Blood Group: A+, A-, B+, B-, AB+, AB-, O+, O-
City:        Current city/location
```

### Optional Fields
```
Medical Conditions: Any diseases or health issues
                   (e.g., "Diabetes", "High BP", "None")
```

### Validation Rules
- All required fields must be filled
- Age must be between 18 and 65
- Email must be valid format
- Phone number must be valid
- Blood group must be selected from dropdown

---

## 🔄 Data Flow

### Registration Process
```
1. User clicks "Blood Donation" card
2. Modal popup opens
3. User fills form
4. Click "Register as Donor"
5. Data sent to backend API
6. Backend saves to MySQL database
7. Success message shown
8. Modal closes automatically
9. Donors list refreshes
10. New donor appears in Blood Alerts
```

### Viewing Donors Process
```
1. Page loads
2. Automatically fetches all donors from API
3. Displays donors in grid layout
4. Each donor card shows complete details
5. Real-time updates when new donor registers
```

---

## 🔌 API Integration

### Register Donor
```javascript
POST http://localhost:8080/api/donors/add

Request Body:
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "9876543210",
  "gender": "Male",
  "age": 25,
  "bloodGroup": "O+",
  "city": "Bengaluru",
  "disease": "None",
  "availability": "AVAILABLE"
}

Response: Donor object with ID
```

### Get All Donors
```javascript
GET http://localhost:8080/api/donors/all

Response: Array of donor objects
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "gender": "Male",
    "age": 25,
    "bloodGroup": "O+",
    "city": "Bengaluru",
    "disease": "None",
    "availability": "AVAILABLE",
    "createdAt": "2026-05-23T10:30:00"
  }
]
```

---

## 💡 Usage Tips

### For Donors
1. **Keep Information Updated**: Ensure your contact details are current
2. **Mention Medical Conditions**: Be honest about health issues
3. **Update Availability**: Let others know if you're available to donate
4. **Respond Quickly**: When contacted for emergency, respond promptly

### For Those Seeking Blood
1. **Check Blood Alerts**: Browse registered donors by blood group
2. **Contact Multiple Donors**: Increase chances of finding match
3. **Be Respectful**: Donors are volunteers helping save lives
4. **Provide Details**: When contacting, explain the emergency clearly

---

## 🎯 User Journey Example

### Scenario: Registering as a Donor
```
1. User logs in → Redirected to dashboard
2. Sees 4 main cards with beautiful animations
3. Clicks "Blood Donation" card
4. Popup modal appears with form
5. Fills in:
   - Name: "Rajesh Kumar"
   - Email: "rajesh@example.com"
   - Phone: "9876543210"
   - Gender: "Male"
   - Age: 28
   - Blood Group: "O+"
   - City: "Bengaluru"
   - Medical Conditions: "None"
6. Clicks "Register as Donor"
7. Success message: "✓ Successfully registered as a donor!"
8. Modal closes after 2 seconds
9. Scrolls down to see their card in Blood Alerts
10. Their information is now visible to everyone
```

### Scenario: Finding a Donor
```
1. User opens dashboard
2. Scrolls to "Registered Blood Donors" section
3. Sees grid of donor cards
4. Looks for specific blood group (e.g., "O+")
5. Finds matching donor card
6. Sees complete details:
   - Name, Blood Group badge
   - Email, Phone
   - Age, Gender
   - City, Medical conditions
   - Availability status
7. Contacts donor via phone or email
8. Explains emergency situation
9. Coordinates blood donation
```

---

## 🔐 Security & Privacy

### Data Protection
- All donor information is stored securely in MySQL database
- HTTPS recommended for production
- Input validation on both frontend and backend
- SQL injection protection via JPA

### Privacy Considerations
- Donors voluntarily share their information
- Contact details visible to all users
- Medical conditions are optional
- Users can update/remove their information (future feature)

---

## 🐛 Troubleshooting

### Issue: Modal doesn't open
**Solution**: Check browser console for JavaScript errors

### Issue: Form submission fails
**Solution**: 
- Ensure backend is running
- Check database connection
- Verify all required fields are filled

### Issue: Donors not loading
**Solution**:
- Check backend API is accessible
- Verify database has donor records
- Check browser console for errors

### Issue: Styling looks broken
**Solution**:
- Clear browser cache (Ctrl+F5)
- Check if CSS loaded properly
- Verify internet connection (for Google Fonts)

---

## 📱 Responsive Design

### Desktop (>900px)
- 4 cards in 2x2 grid
- Full navigation menu visible
- Large donor cards in grid

### Mobile (<900px)
- Cards stack vertically
- Navigation menu hidden
- Donor cards stack in single column
- Touch-friendly buttons

---

## 🎨 Customization Options

### Change Colors
Edit CSS variables in dashboard.html:
```css
/* Primary color (Red) */
#ff4d4d → Your color

/* Secondary color (Blue) */
#4da6ff → Your color
```

### Modify Form Fields
Add/remove fields in the modal form section

### Adjust Card Layout
Change grid-template-columns in .dashboard-grid

---

## ✅ Testing Checklist

- [ ] Dashboard loads with animations
- [ ] All 4 cards are visible
- [ ] Blood Donation card opens modal
- [ ] Form validation works
- [ ] Donor registration successful
- [ ] Success message appears
- [ ] Modal closes automatically
- [ ] Donors list loads
- [ ] Donor cards display correctly
- [ ] All donor details visible
- [ ] Blood Alerts card scrolls to donors
- [ ] Emergency cards redirect properly
- [ ] Logout button works
- [ ] Responsive on mobile

---

## 🚀 Future Enhancements

### Planned Features
- [ ] Edit donor information
- [ ] Delete donor registration
- [ ] Search/filter donors by blood group
- [ ] Sort donors by city
- [ ] Donor availability toggle
- [ ] Email notifications
- [ ] SMS alerts for emergencies
- [ ] Donor history tracking
- [ ] Rating system for donors
- [ ] Map view of nearby donors

---

## 📞 Support

For issues or questions:
- Email: support@helphub.com
- Phone: +91 9876543210

---

**Dashboard Ready! Start saving lives! 🩸❤️**
