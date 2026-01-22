# Preference Center API

This project is a backend service that manages users and their communication preferences (consents).

The application is fully **Dockerized** and includes test coverage enforcement and an API testing collection.

---

## Requirements

To run the project you need:

* **Docker**
* **Docker Compose**
* *(Optional, for local execution)* Java 21 and Maven

---

## Project Structure (Relevant Files)

* `docker-compose.yml` – Runs the application and PostgreSQL
* `Dockerfile` – Builds the Spring Boot application image
* `application.properties` – Runtime configuration (PostgreSQL)
* `application-test.properties` – Test configuration (H2 in-memory DB)
* `Preference_Center.postman_collection.json` – Postman collection for API testing
* `pom.xml` – Maven configuration (including JaCoCo)

---

## Running the Application (Recommended)

The easiest way to run the project is using Docker Compose.

### Build and start the application

From the project root:

```bash
  docker-compose up --build
```

This will start:

* PostgreSQL on port `5432`
* The application on port `8080`

The application will be available at:

```
http://localhost:8080
```

---

## Runtime Configuration

The application uses environment variables provided by Docker Compose.

### `application.properties`

This file is used at runtime and expects the following variables:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

These are automatically set in `docker-compose.yml`.

---

## Running Tests and Coverage

The project includes unit tests and enforces **high code coverage** using **JaCoCo**.

### Run tests

```bash
  mvn clean verify
```

During tests:

* An **H2 in-memory database** is used
* Configuration is loaded from `application-test.properties`
* PostgreSQL is **not required**

---

### Code Coverage

JaCoCo is configured with a **minimum instruction coverage of 99%**.

After running tests, the coverage report is generated at:

```bash
  target/site/jacoco/index.html
```

Some infrastructure classes are intentionally excluded from coverage checks.

---

## API Testing with Postman

A Postman collection is included in the repository:

```
Preference_Center.postman_collection.json
```

### How to use it

1. Open Postman
2. Import the collection
3. Set the base URL variable to:

```
http://localhost:8080
```

The collection includes requests for:

* Creating a user
* Getting a user with current consents
* Updating user email
* Deleting a user
* Updating user consents

---

## API Endpoints Overview

### Users

* `POST /api/users` – Create a user
* `GET /api/users/{id}` – Get user with current consents
* `PATCH /api/users/{id}` – Update user email
* `DELETE /api/users/{id}` – Delete user

### Consents

* `PUT /api/consents` – Update user consents

---