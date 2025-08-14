# Janitri Dashboard Login Automation (POM + Selenium)  

> *A beginner-friendly automation framework created as part of a QA internship assignment to test the Janitri Dashboard login page.*  

## 📌 Project Overview  
This project is part of my QA internship learning journey.  
It automates the **login page functionality** of the [Janitri Dashboard](https://dev-dash.janitri.in) using:  
- **Java**  
- **Selenium WebDriver**  
- **TestNG**  
- **Page Object Model (POM)** design pattern  

Alongside automation, the repository contains **manual test cases** covering positive, negative, UI, and boundary scenarios.  

---

## 🗂 Repository Structure  
├── screenshots/ # Screenshots of execution or UI
├── src/ # Java source files
│ ├── main/java/com/janitri/pages/ # Page Object classes
│ │ └── LoginPage.java
│ ├── test/java/com/janitri/tests/ # Test classes
│ │ └── LoginTest.java
│ └── base/BaseTest.java # Browser setup & teardown
├── janitri_login_testcases.xlsx # Manual test cases
├── pom.xml # Maven dependencies
├── testng.xml # TestNG suite file
└── README.md # Project documentation



---

## ✅ Features Automated  
- Verify login with blank fields  
- Verify login with random credentials (invalid login)  
- Validate password masking and visibility toggle  
- Check presence of UI elements (title, placeholders, input fields, eye icon)  
- Basic boundary checks for input fields  

---

## 📄 Manual Test Cases  
The file **`janitri_login_testcases.xlsx`** contains:  
- Positive scenarios  
- Negative scenarios  
- UI validation scenarios  
- Boundary value scenarios  

Each test case includes:  
- **Test Case ID**  
- **Test Scenario**  
- **Test Steps**  
- **Expected Result**  
- **Actual Result**  
- **Status**  

---

## ⚙️ How to Run the Automation  
1. **Clone the Repository**  
   ```bash
   git clone https://github.com/vaibhavkulal/janitri-login-automation.git
   cd janitri-login-automation
2. **Install Dependencies**
Make sure you have Java JDK 8+ and Maven installed.
```bash
mvn clean install
3. **Run Tests**
mvn test

## 🛠 Tools & Technologies

Java 11

Selenium WebDriver 4.x

TestNG

Maven

Page Object Model (POM)

##📌 Notes

This project was created as part of my learning process in QA automation.
While it is a functional test suite, it’s also a step in my journey to deepen my skills in Java, Selenium, and test design patterns.
