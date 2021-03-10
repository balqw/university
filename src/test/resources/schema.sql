drop table if exists student;
drop table if exists educator cascade;
drop table if exists id_card cascade;
drop table if exists educator_card;
drop table if exists class_room cascade;
drop table if exists lesson cascade;
drop table if exists lesson_group cascade;
drop table if exists _group cascade;

create table _group
(
    group_id    serial primary key,
    abbreviate  varchar(10) unique,
    description varchar(200)
);

create table student
(
    student_id serial primary key,
    first_name  varchar(100),
    last_name   varchar(100),
    course     integer,
    group_id   int references _group (group_id)
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


INSERT INTO _group (group_id, abbreviate, description)
VALUES (1,'abbr1','desc1');
INSERT INTO _group (group_id, abbreviate,description)
VALUES (2,'abbr2','desc2');
INSERT INTO _group (group_id, abbreviate,description)
VALUES (3,'abbr3','desc3');

INSERT INTO student (student_id, first_name, last_name, course, group_id)
VALUES (1,'John1','John1',1,1);
INSERT INTO student (student_id, first_name, last_name, course, group_id)
VALUES (2,'John2','John2',2,2);
INSERT INTO student (student_id, first_name, last_name, course, group_id)
VALUES (3,'John3','John3',3,2);