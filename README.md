# purchaseandearn

This project is a Spring Boot application that calculates reward points for customers based on Customer  transactions Every month.
Shows us the total rewards earned by a customer every month and also cumulative rewards earned in last three months

## Features

- Calculate reward points for each customer per month and total.
- RESTful endpoint to fetch the total points earned every month and in the last three months.
- Asynchronous API calls.

## Getting Started

### Prerequisites

- Java 8 or higher
- Gradle

### Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run `./gradlew bootRun` to start the Spring Boot application.

### API Endpoints

- `GET  /v1/rewardSummary/1{customerId}`: Get total points earned every month and in the last three months for a customer.

### Testing

Run `./gradlew test` to execute the test cases.

