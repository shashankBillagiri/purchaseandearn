## Version Information

- Application Version: 1.0.0
- JDK Version: 8
- Spring Boot Version: 2.7.10

# purchaseandearn

This project is a Spring Boot application that calculates reward points for customers based on their transactions every month. It shows the total rewards earned by a customer each month and also the cumulative rewards earned in the last three months.

## Features

- Calculate reward points for each customer per month and in total.
- RESTful endpoint to fetch the total points earned every month and in the last three months.
- Asynchronous API calls.
- Integrated Log4j2 for detailed logging of application events.
- Implemented robust exception handling to manage cases where a customer does not exist, providing clear error messages.

## Getting Started

### Prerequisites

- Java 8 or higher

### Database Setup

- This application uses the H2 in-memory database for test data insertion.

- **SQL Scripts**
    - `src/main/resources/schema.sql`: Contains the SQL statements to create the necessary tables.
    - `src/main/resources/data.sql`: Contains the SQL statements to insert test data into the tables.

- Database configuration properties are specified in the `src/main/resources/application.properties` file.

### Running the Application

1. Clone the repository.
2. Navigate to the project directory.
3. Run `./gradlew bootRun` to start the Spring Boot application.

### API Endpoints

- `GET /v1/rewardSummary/{customerId}`: Get total points earned every month and in the last three months for a customer.

### Logging

- **Log4j2**: Logs are configured using Log4j2, providing detailed information on application operations and error handling.

### Exception Handling

- Custom exception handling is implemented to manage scenarios where a customer does not exist. If a request is made with an invalid customer ID, a `404 Not Found` response with a descriptive error message will be returned.

### Testing

- Run `./gradlew test` to execute the test cases.
