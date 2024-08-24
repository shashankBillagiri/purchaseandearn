## Version Information

- Application Version: 1.0.0
- JDk Version: 8
- Spring Boot Version: 2.7.10



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


### Database Setup

- This application uses the H2 in-memory database for test data insertion.

- SQL Scripts
          To set up the database schema and insert initial data, the following SQL scripts are provided:
        - src/main/resources/schema.sql: Contains the SQL statements to create the necessary tables.
        -  src/main/resources/data.sql: Contains the SQL statements to insert test data into the tables.

- Database configuration properties are specified in the src/main/resources/application.properties file.


### Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run `./gradlew bootRun` to start the Spring Boot application.

### API Endpoints

- `GET  /v1/rewardSummary/1{customerId}`: Get total points earned every month and in the last three months for a customer.

### Testing

Run `./gradlew test` to execute the test cases.

