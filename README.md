# -E-Commerce-Microservices-Project-Java-Spring-Boot
This is a scalable and modular E-Commerce Backend System built using Java Spring Boot Microservices Architecture. The system is split into independently deployable services such as Product Service, Authentication Service, and Payment Service, allowing for better scalability, flexibility, and maintainability.
This is a scalable and modular E-Commerce Backend System built using Java Spring Boot Microservices Architecture. The system is divided into independently deployable services, including Product Service, Authentication Service, and Payment Service, which enables better scalability, flexibility, and maintainability.
## 🔧 Microservices Included
- **Product Service** – Manages products and inventory.
- **Auth Service** – User registration, login, JWT security.
- **Payment Service** – Simulates payment handling for orders.

## 🧱 Architecture
- Spring Boot + REST APIs
- JWT Authentication
- MySQL
- Role-based access (Admin, User)
- Clean microservices separation

## 🚀 Getting Started

```bash
# Clone the project
git clone https://github.com/yourusername/ecommerce-microservices.git

# Navigate to each service and run it individually

🔧 Microservices Overview
📦 Product Service
Handles all product-related operations (CRUD).

Stores product information, inventory, and pricing.

Provides APIs for adding, updating, listing, and deleting products.

🔐 Authentication Service
Manages user registration and login.

Implements JWT-based authentication and role-based access control (Admin, Customer).

Ensures secure access to protected APIs using Spring Security.

💳 Payment Service
Simulates secure payment processing.

Handles transaction requests during checkout.

Sends success/failure responses based on mock logic or simulated gateways.

⚙️ Tech Stack
Language: Java 17+

Framework: Spring Boot, Spring Security

Authentication: JWT (JSON Web Token)

Database: MySQL / PostgreSQL

Architecture: Microservices

Communication: REST APIs

Build Tool: Maven

Version Control: Git & GitHub

Tools: Postman, Swagger (for API testing/documentation)
cd product-service
mvn spring-boot:run
