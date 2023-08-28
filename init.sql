CREATE EXTENSION IF NOT EXISTS "pg_trgm";

CREATE TABLE IF NOT EXISTS person(
    id          UUID PRIMARY KEY,
    nickname    VARCHAR(32) NOT NULL UNIQUE,
    name        VARCHAR(100) NOT NULL,
    birthday    DATE         NOT NULL,
    stack       TEXT
);

CREATE INDEX IF NOT EXISTS idx_person_nickname ON person USING gist (nickname gist_trgm_ops(siglen = 64));
CREATE INDEX IF NOT EXISTS idx_person_name ON person USING gist (name gist_trgm_ops(siglen = 64));
CREATE INDEX IF NOT EXISTS idx_person_stack ON person USING gist (stack gist_trgm_ops(siglen = 64));