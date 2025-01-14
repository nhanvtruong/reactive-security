--liquibase formated sql
--changeset nhanvtruong:202501100329
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS tbl_tokens (
    session_id         UUID PRIMARY KEY NOT NULL,
    subject            VARCHAR NOT NULL,
    revoked            BOOLEAN DEFAULT FALSE,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

