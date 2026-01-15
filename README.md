**Testing Framework – Spring Boot Application
Project Overview**
Agile Methodology Document - Attached in Repo

This project is a Maven-based Spring Boot Testing Framework designed to manage, execute, and analyze API and UI test cases.
It provides REST APIs, execution scheduling, analytics dashboards, and UI pages for monitoring test runs and results.

**The framework supports:**

API testing (REST-based) 

UI testing (Selenium WebDriver)

Test execution scheduling

Result tracking and analytics

Dashboard-based monitoring



**Features
Test Case Management**

Create, update, retrieve, and delete test cases

Support for API and UI test types

Test Execution

Execute tests on demand

Schedule test executions

Track execution status and timestamps

Analytics

Failure trend analysis

Execution history insights

Dashboard-based visualization

Web Interface

Dashboard view

Execution monitoring page

Analytics dashboard using Thymeleaf templates

**Technology Stack**

**Backend:** Java, Spring Boot

**Build Tool:** Maven

**Database:** JPA / Hibernate

**API Testing:** REST Assured

**UI Testing:** Selenium WebDriver

**Frontend:** Thymeleaf

**Version Control:** Git

**Key Components**
**Controllers**

**AnalyticsController** – Analytics-related APIs

**ExecutionController** – Test execution and scheduling

**TestCaseController** – CRUD operations for test cases

**UiController** – Web UI endpoints

**Services**

**TestCaseService** – Test case business logic

**ExecutionService** – Execution and scheduling logic

**AnalyticsService** – Analytics calculations

**Execution Layer**

**ApiTestRunner** – Runs API tests

**SeleniumTestRunner** – Runs UI tests

**ExecutionTask** – Execution task handling

Models (Entities)

TestCase

TestSuite

ExecutionRun

TestResult

Configuration

**All application configurations are managed in:**

src/main/resources/application.properties


**This includes:**

Database configuration

Server port

Application-level settings

How to Run the Project
Prerequisites

Java 8 or higher

Git

**Steps**
git clone <repository-url>
cd testingFramework
./mvnw spring-boot:run


**For Windows:**

mvnw.cmd spring-boot:run

**Running Tests**
./mvnw test

Future Enhancements

Authentication and role-based access

CI/CD pipeline integration

Report export (PDF/Excel)

Parallel test execution

Cloud browser support

**Author**

Yogasri Kandepalli
Spring Boot | Java | Automation Testing
