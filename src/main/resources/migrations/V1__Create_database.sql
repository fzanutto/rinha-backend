CREATE TABLE IF NOT EXISTS person(
    id          UUID PRIMARY KEY,
    nickname    VARCHAR(50) NOT NULL UNIQUE,
    name        VARCHAR(150) NOT NULL,
    birthday    DATE         NOT NULL,
    stack       TEXT
);

CREATE INDEX idx_person_name ON person (birthday);
CREATE INDEX idx_person_stacks ON person (stack);