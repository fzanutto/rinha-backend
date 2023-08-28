CREATE EXTENSION IF NOT EXISTS "pg_trgm";

CREATE TABLE IF NOT EXISTS person(
    id          UUID PRIMARY KEY,
    nickname    VARCHAR(32) NOT NULL UNIQUE,
    name        VARCHAR(100) NOT NULL,
    birthday    DATE         NOT NULL,
    stack       TEXT[],
    search      TEXT
);

CREATE INDEX IF NOT EXISTS idx_person_search ON person USING gist (search gist_trgm_ops(siglen = 64));