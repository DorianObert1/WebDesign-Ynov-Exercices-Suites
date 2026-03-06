CREATE TABLE IF NOT EXISTS app_users (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255),
    email   VARCHAR(255),
    active  BOOLEAN DEFAULT TRUE
);
