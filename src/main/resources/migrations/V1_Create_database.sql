CREATE TABLE IF NOT EXISTS PEOPLE(
    id          UUID PRIMARY KEY,
    nick_name   VARCHAR(50) NOT NULL UNIQUE,
    person_name VARCHAR(150) NOT NULL,
    date_birth  DATE         NOT NULL,
    stacks      TEXT NOT NULL
);

CREATE INDEX idx_people_nick_name ON PEOPLE (nick_name);
CREATE INDEX idx_people_people_name ON PEOPLE (people_name);
CREATE INDEX idx_stack_stack_name ON PEOPLE (stacks);