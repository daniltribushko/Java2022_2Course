create table moduleScores(
    id SERIAL primary key ,
    name VARCHAR(255) NOT NULL ,
    scoreByExercise INTEGER NOT NULL ,
    scoreByControlQuestion INTEGER NOT NULL ,
    scoreByHomeWork INTEGER NOT NULL ,
    module INTEGER references  modules(id) on DELETE cascade on UPDATE cascade,
    student INTEGER references students(id) on DELETE cascade on UPDATE cascade
)