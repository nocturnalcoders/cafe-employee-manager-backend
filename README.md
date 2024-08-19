Café Employee Manager Backend

Overview

The Café Employee Manager Backend is a Spring Boot application designed to manage cafés and employees. This backend application provides RESTful APIs to handle CRUD operations for café and employee data.

Features

Café Management: Create, read, update, and delete café records.
Employee Management: Manage employee records including their association with cafés.
Configuration: Customizable server and database configurations.
Requirements

Java 17: The application is built with Java 17.
MySQL 8: The application uses MySQL 8 as the database.
Getting Started

Prerequisites
Java 17: Ensure that Java 17 is installed and configured.
MySQL: Install and set up MySQL. Create a database named cafe-employee.

Configuration
Update the application.yml file in the src/main/resources directory with your database credentials and other configurations.


Building and Running
With Maven

Build the Project:
<br>Run the following command to build the project:<br>
mvn clean package

Run the Application: 
Start the application using the command:<br>
mvn spring-boot:run

With Docker

Build the Docker Image: Build the Docker image using the provided Dockerfile:<br>
docker build -t cafe-employee-manager-backend .

Run the Docker Container:
<br>Start the Docker container: <br>
docker run -p 8000:8000 --name cafe-employee-manager-backend cafe-employee-manager-backend

API Endpoints

Café Endpoints
Create Café: POST /cafe-employee/api/v1/cafes
<br>
Update Café: PUT /cafe-employee/api/v1/cafes/{id}
<br>
Delete Café: DELETE /cafe-employee/api/v1/cafes/{id}
<br>
List Cafés: GET /cafe-employee/api/v1/cafes

Employee Endpoints
<br>
Create Employee: POST /cafe-employee/api/v1/employees
<br>
Update Employee: PUT /cafe-employee/api/v1/employees/{id}
<br>
Delete Employee: DELETE /cafe-employee/api/v1/employees/{id}
<br>
List Employees: GET /cafe-employee/api/v1/employees
