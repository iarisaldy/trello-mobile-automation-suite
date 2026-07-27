# 🚀 Trello & Mobile Test Automation Suite

<div align="center">

![Java](https://img.shields.io/badge/Language-Java%208%2B-007396?style=for-the-badge&logo=java&logoColor=white)
![TestNG](https://img.shields.io/badge/Testing-TestNG-FF6F00?style=for-the-badge)
![RestAssured](https://img.shields.io/badge/API-REST%20Assured-2E7D32?style=for-the-badge)
![Appium](https://img.shields.io/badge/Mobile-Appium%20%7C%20Selenium-660066?style=for-the-badge)
![Build](https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

<p align="center">
  <b>Comprehensive Automation Testing Framework for Trello REST APIs and Android Mobile Applications</b>
</p>

</div>

---

## 📌 Project Overview

This repository contains an end-to-end QA Automation framework built with **Java**, **TestNG**, **REST Assured**, and **Appium/Selenium**. It covers automated testing across two key layers:

1. **API Automation**: Trello REST API end-to-end lifecycle testing (Boards, Lists, Cards management).
2. **Mobile UI Automation**: Android Application user interface flow validation (Login, Product catalog, Shopping Cart, Checkout).

---

## 🛠 Tech Stack

- **Programming Language**: Java (JDK 8+)
- **Test Runner & Assertions**: TestNG, Hamcrest
- **API Testing**: REST Assured
- **Mobile UI Testing**: Appium Java Client, Selenium WebDriver
- **Build Tool**: Apache Maven

---

## 📂 Project Structure

```text
trello-mobile-automation-suite/
├── src/
│   ├── main/
│   │   └── java/com/automation/
│   │       └── TestRunner.java       # Custom Test Execution Runner
│   └── test/
│       ├── java/com/automation/
│       │   ├── APITests.java        # Trello REST API Automated Tests
│       │   └── UITests.java         # Android Appium UI Automated Tests
│       └── resources/
│           └── testng.xml           # TestNG Suite Configuration
└── pom.xml                          # Maven Project Dependencies
```

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK 8 or higher)
- Apache Maven
- Android Studio / Android Emulator & Appium Server (for UI tests)
- Active Trello API Key & Token (for API tests)

### Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/iarisaldy/trello-mobile-automation-suite.git
   cd trello-mobile-automation-suite
   ```

2. Configure Trello API credentials in `APITests.java`:
   ```java
   private static final String KEY = "YOUR_TRELLO_API_KEY";
   private static final String TOKEN = "YOUR_TRELLO_API_TOKEN";
   ```

---

## 🧪 Test Execution

### Run All Test Suites via Maven

```bash
mvn clean test
```

### Run Specific Test Suite

- **API Tests (Trello REST API)**:
  ```bash
  mvn clean test -Dtest=APITests
  ```

- **UI Tests (Android Appium)**:
  ```bash
  mvn clean test -Dtest=UITests
  ```

---

<div align="center">

Maintained by **[Irfan Arisaldy](https://github.com/iarisaldy)**

</div>