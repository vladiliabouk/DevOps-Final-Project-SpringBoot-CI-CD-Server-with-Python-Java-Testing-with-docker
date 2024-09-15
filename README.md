# DevOps Final Project - SpringBoot CI/CD Server with Python & Java Testing with docker

## Introduction

This is a comprehensive DevOps final project, divided into three main parts:

1. **DevOps Server with Spring Boot**: Developing a CI/CD automation server using Spring Boot.
2. **Python Tests**: Creating tests to validate the functionality of the server.
3. **Docker Orchestration**: Using Docker Compose for orchestrating the server and tests.

Each part of the project incorporates essential DevOps practices and tools to create a unified continuous deployment pipeline.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Setup and Installation](#setup-and-installation)
3. [Part 1: DevOps Server](#part-1-devops-server)
4. [Part 2: Python Tests](#part-2-python-tests)
5. [Part 3: Docker Orchestration](#part-3-docker-orchestration)
6. [Contributions](#contributions)
7. [License](#license)

## Project Structure

- `server/`: Spring Boot application for CI/CD automation.
- `tester/`: Python tests for validating the server.
- `docker/`: Dockerfiles and Docker Compose configuration.

## Setup and Installation

### Prerequisites

- Java 11 or higher
- Python 3.8 or higher
- Docker
- Docker Compose

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/vladiliabouk/FinalProjectInDevOps.git
   cd FinalProjectInDevOps
   
2. **Setup Spring Boot Server**

   Navigate to the server directory and follow the instructions to build and run the Spring Boot application.

3.**Setup Python Environment**

   Navigate to the tester directory and install the required packages:
   pip install -r requirements.txt
   
4.**Build and Run with Docker**

    Navigate to the docker directory and use Docker Compose to build and run the services:
    docker-compose up --build

    
**Part 1: DevOps Server**

**Overview**
This part involves developing a CI/CD automation server using Spring Boot. The server manages CI/CD jobs and provides endpoints for CRUD operations.

**Features**
Spring Boot project with dependencies: Spring Web, Spring Data JPA, H2 Database, Lombok.
CRUD operations for CI/CD jobs.
Integration with H2 Database.
Unit, integration, and exception tests.

**Endpoints**
GET /jobs: Retrieve all jobs.

GET /jobs/{id}: Retrieve a job by ID.

GET /jobs/status/{status}: Retrieve jobs by status.

GET /jobs/jobType/{jobType}: Retrieve jobs by job type.

GET /jobs/date-range: Retrieve jobs by a date range.

POST /jobs: Create a new job.

PUT /jobs/{id}: Update a job.

DELETE /jobs/{id}: Delete a job.


**Testing**
Unit Tests (JUnit):
Test individual methods in the service class for specific input/output scenarios. Examples: testAddJob(), testGetJob().
Parameterized Tests (@ParameterizedTest): Test methods with multiple input parameters. Examples: testAddJobWithVariousStatuses(), testGetJobByDifferentIds().
Nested Tests (@Nested): Group related test cases for better organization. Examples: CI/CDJobServiceTests with nested classes for CRUD operations.
Exception Tests (assertThrows): Verify that methods throw expected exceptions. Examples: testDeleteNonExistentJob(), testUpdateJobWithInvalidData().
Integration Tests (@SpringBootTest): Test the interaction between multiple components. Example: CI/CDJobControllerTest class with @SpringBootTest.

**Part 2: Python Tests**

**Overview**
This part involves creating Python tests to validate the functionality of the CI/CD server.

**Features**
Automated tests for endpoints.
Logging configuration and fixtures.
Use of pytest for running tests.

**Test Functions**
test_get_all_jobs()

test_create_job()

test_get_job_by_id()

test_update_job_status()

test_delete_job()

**Part 3: Docker Orchestration**

**Overview**
This part involves orchestrating the CI/CD automation server and Python tests using Docker Compose.

**Features**
Docker Compose configuration for multiple services: Spring Boot application, Redis, PostgreSQL, Python tests.
Custom networking and volume mappings.

**Docker Compose Services**
cd-server: Spring Boot application.
redis: Caching service.
db: PostgreSQL database.
tester: Python testing service.
