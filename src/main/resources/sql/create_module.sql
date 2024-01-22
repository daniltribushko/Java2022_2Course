create table modules(
    id SERIAL primary key,
    name VARCHAR(255) NOT NULL ,
    maxScoreByExercise INTEGER NOT NULL ,
    maxScoreByControlQuestion INTEGER NOT NULL ,
    maxScoreByHomeWork INTEGER NOT NULL
);