create table students(
    id SERIAL primary key ,
    fullName VARCHAR(255) NOT NULL ,
    uLearnID VARCHAR(255) NOT NULL ,
    "group" INTEGER NOT NULL
);