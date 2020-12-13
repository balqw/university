drop table if exists student;
drop table if exists educator cascade;
drop table if exists idCard cascade;
drop table if exists educatorCard;
drop table if exists classRoom cascade;
drop table if exists lesson cascade;

create table student
(
    studentId serial primary key,
    firstName varchar(100),
    lastName  varchar(100),
    course    integer
);

create table idCard
(
    cardId     serial primary key,
    dateExpire timestamp
);

create table educator
(
    educatorId serial primary key,
    firstName  varchar(100),
    lastName   varchar(100),
    idCard     int references idCard (cardId)
);

create table classRoom
(
    classId  serial primary key,
    number   int unique,
    capacity int
);

create table lesson
(
    lessonId    serial primary key,
    title       varchar(100),
    startLesson timestamp,
    endLesson   timestamp,
    classRoom   int references classRoom (number)
);
