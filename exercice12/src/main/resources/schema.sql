CREATE TABLE IF NOT EXISTS orders (
    id             BIGSERIAL PRIMARY KEY,
    customer_name  VARCHAR(255),
    total_amount   DOUBLE PRECISION,
    status         VARCHAR(50) DEFAULT 'PENDING',
    created_at     TIMESTAMP
);
