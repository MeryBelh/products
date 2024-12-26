# Product Management API

This is a Spring Boot 3 application that manages products and user authentication, running inside Docker with PostgreSQL as the database. The application supports JWT authentication and allows users to manage a list of products, a shopping cart, and a wishlist.

## Requirements

- Java 17 or higher
- Docker
- Docker Compose

## Project Structure

- **Backend**: Spring Boot 3
- **Database**: PostgreSQL
- **API Documentation**: Swagger UI
- **Database Initialization**: Liquibase

## How to Run the Application

1. **Clone the repository**:
   ```bash
   git clone <repository_url>
   cd <project_directory>
Build and Run the application: Run the following commands to build and start the application using Docker Compose:

bash
Copier le code
mvn clean package
docker-compose up --build
Access the API: Once the application is running, you can access the Swagger UI to explore the API:

Open your browser and navigate to:
http://localhost:8080/swagger-ui/index.html