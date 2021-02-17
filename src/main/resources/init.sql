drop table if exists student_group cascade;
drop table if exists student;
drop table if exists educator cascade;
drop table if exists id_card cascade;
drop table if exists educator_card;
drop table if exists class_room cascade;
drop table if exists lesson cascade;

create table group
(
    group_id    serial primary key,
    abbreviate  varchar(10) unique,
    description varchar(200)
);

create table student
(
    student_id serial primary key,
    firstName  varchar(100),
    lastName   varchar(100),
    course     integer,
    group_id   int references group (group_id)
);

create table id_card
(
    card_id     serial primary key,
    date_expire timestamp
);

create table educator
(
    educator_id serial primary key,
    first_name  varchar(100),
    last_name   varchar(100),
    id_card     int references id_card (card_id)
);

create table class_room
(
    class_id serial primary key,
    number   int unique,
    capacity int
);

create table lesson
(
    lesson_id    serial primary key,
    title        varchar(100),
    start_lesson timestamp,
    end_lesson   timestamp,
    class_room   int references class_room (number)
);

create table lesson_group
(
    id        serial primary key,
    lesson_id int references lesson (lesson_id),
    group_id  int references group (group_id)
);

