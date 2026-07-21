CREATE TABLE order_line
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_overview_id UUID           NOT NULL REFERENCES order_overview (id) ON DELETE CASCADE,
    product_name      VARCHAR(255)   NOT NULL,
    price             NUMERIC(10, 2) NOT NULL,
    amount            INTEGER        NOT NULL
);