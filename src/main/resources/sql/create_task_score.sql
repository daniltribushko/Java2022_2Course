create table taskScores(
    id SERIAL primary key ,
    score INTEGER NOT NULL ,
    task INTEGER references tasks(id) on DELETE cascade on UPDATE cascade ,
    student INTEGER references students(id) on DELETE cascade on UPDATE cascade
);