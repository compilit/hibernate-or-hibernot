CREATE TABLE order_overview
(
    id          UUID PRIMARY KEY,
    customer_id VARCHAR(255),
    created_at  TIMESTAMP NOT NULL DEFAULT now(),
    version     BIGINT DEFAULT 0
);