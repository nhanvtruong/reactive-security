--liquibase formated sql
--changeset nhanvtruong:202501100329

CREATE TABLE IF NOT EXISTS tbl_tokens (
    session_id         UUID PRIMARY KEY NOT NULL,
    subject            VARCHAR NOT NULL,
    expired            BOOLEAN DEFAULT FALSE,
    created_by         VARCHAR,
    created_date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   VARCHAR,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

