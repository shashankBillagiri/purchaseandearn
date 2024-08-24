-- Queries to Insert sample data in Customer Table
INSERT INTO CUSTOMER (NAME)
VALUES ('John');
INSERT INTO CUSTOMER (NAME)
VALUES ('Jason');
INSERT INTO CUSTOMER (NAME)
VALUES ('Alina');


-- Query to Insert sample data in Transaction Table
INSERT INTO PURCHASES (DATE, AMOUNT, CUSTOMER_ID)
VALUES ('2024-08-24', 20.75, 1),
       ('2024-08-20', 200.00, 1),
       ('2024-07-25', 120.00, 1),
       ('2024-07-23', 75.00, 1),
       ('2024-06-25', 99.33, 1);