CREATE TABLE customer
(
    email      VARCHAR(255) PRIMARY KEY,
    password   VARCHAR(255) NOT NULL,
    version    BIGINT DEFAULT 0
);