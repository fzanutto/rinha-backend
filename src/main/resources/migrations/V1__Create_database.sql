CREATE EXTENSION IF NOT EXISTS "pg_trgm";

CREATE TABLE IF NOT EXISTS person(
    id          UUID PRIMARY KEY,
    nickname    VARCHAR(50) NOT NULL UNIQUE,
    name        VARCHAR(150) NOT NULL,
    birthday    DATE         NOT NULL,
    stack       TEXT
);

CREATE INDEX idx_person_name ON person (name);
CREATE INDEX idx_person_stack ON person USING gist (search gist_trgm_ops(siglen = 64));