create table modules
(
    id                        SERIAL primary key,
    name                      VARCHAR(255) NOT NULL,
    maxScoreByExercise        INTEGER      NOT NULL,
    maxScoreByControlQuestion INTEGER      NOT NULL,
    maxScoreByHomeWork        INTEGER      NOT NULL
);

create table moduleScores
(
    id                     SERIAL primary key,
    scoreByExercise        INTEGER NOT NULL,
    scoreByControlQuestion INTEGER NOT NULL,
    scoreByHomeWork        INTEGER NOT NULL,
    module                 INTEGER references modules (id) on DELETE cascade on UPDATE cascade,
    student                INTEGER references students (id) on DELETE cascade on UPDATE cascade
);

create table tasks
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    type     VARCHAR(255) NOT NULL,
    maxScore INTEGER      NOT NULL,
    module   INTEGER REFERENCES modules (id) on DELETE cascade on UPDATE cascade
);

create table taskScores
(
    id      SERIAL primary key,
    score   INTEGER NOT NULL,
    task    INTEGER references tasks (id) on DELETE cascade on UPDATE cascade,
    student INTEGER references students (id) on DELETE cascade on UPDATE cascade
);

create table students(
                         id SERIAL primary key ,
                         fullName VARCHAR(255) NOT NULL ,
                         uLearnID VARCHAR(255) NOT NULL ,
                         groupNumber INTEGER NOT NULL
);