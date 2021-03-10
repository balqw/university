drop table if exists student cascade ;
drop table if exists _group cascade ;


create table _group
(
    id serial primary key,
    abbr  varchar(10) unique,
    descr varchar(200)
);

create table student
(
    id serial primary key,
    f_name  varchar(100),
    l_name   varchar(100),
    course     integer,
    group_id   integer
);

INSERT INTO _group (id, abbr, descr)
VALUES (1,'test2','test2');

INSERT INTO student (id, f_name, l_name, course, group_id)
VALUES (1,'test2','test2',1,1);

