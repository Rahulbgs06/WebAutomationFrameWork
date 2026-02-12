# Selenium TestNG Automation Project

## Overview
This project is an **end-to-end automation framework** for web application testing using **Selenium WebDriver** and **TestNG**.  
It performs **login, add-to-cart, and checkout** operations on the demo website [SauceDemo](https://www.saucedemo.com/), supporting both **valid and 
invalid users**.

## Features
- **Data-Driven Testing**: Uses Excel (`LoginData.xlsx`) to manage multiple test scenarios.
- **Page Object Model (POM)**: Clean separation of pages and test logic.
- **TestNG Listeners**: Capture detailed test results, success/failure messages, and screenshots.
- **ExtentReports Integration**: Generates detailed HTML reports with screenshots on failures.
- Supports **positive and negative testing** for login functionality.

## Project Structure
src/
├── main/java/
├── test/java/
│ ├── base/ # Base test setup and WebDriver initialization
│ ├── pages/ # Page classes (LoginPage, HomePage, CartPage, CheckoutPage)
│ ├── tests/ # Test classes (LoginTest)
│ └── utils/ # Utilities (ExcelUtils, ScreenshotUtil, ExtentManager, TestListener)
└── test/resources/testdata/
└── LoginData.xlsx # Excel file for test data
reports/ # ExtentReports HTML files and screenshots


## Prerequisites
- Java 17+
- Maven 3.x+
- Chrome Browser
- Git (for version control)
- IDE (Eclipse, IntelliJ, VS Code, etc.)

## How to Run Tests
1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd <project-folder>
Run tests using Maven:

mvn clean test
Check the ExtentReports generated in the reports/ folder for detailed results including screenshots.

Notes
ChromeDriver is managed automatically using WebDriverManager.

Screenshots are automatically captured on test failures.

Supports end-to-end validation for multiple users via Excel DataProvider.

Author
Rahul Kumar

