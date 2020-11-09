drop table if exists student;
drop table if exists educator cascade ;
drop table if exists idCard cascade;
drop table if exists educatorCard ;
drop table if exists classRoom cascade ;
drop table if exists lesson cascade ;

create table student(
id serial primary key,
firstName varchar(100),
lastName varchar (100),
course integer
);

create table idCard(
id serial primary key,
dateExpire timestamp
);

create table educator(
id serial primary key,
firstName varchar (100),
lastName varchar (100),
idCard int references idCard(id)
);

create table educatorCard(
id serial primary  key,
idCard int references idCard(id) on delete cascade,
idEducator int references educator(id) on delete cascade
);

create table classRoom(
number int primary key,
capacity int
);

create table lesson(
id serial primary key,
title varchar (100),
startLesson timestamp,
endLesson timestamp,
classRoom int
);
