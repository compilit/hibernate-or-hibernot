CREATE TABLE order
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id UUID,
    created_at  TIMESTAMP NOT NULL DEFAULT now()
);