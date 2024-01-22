create table tasks
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    type     VARCHAR(255) NOT NULL,
    maxScore INTEGER      NOT NULL,
    module   INTEGER REFERENCES modules (id) on DELETE cascade on UPDATE cascade
);