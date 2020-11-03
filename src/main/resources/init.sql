drop table if exists students cascade ;
create table students(
id  serial primary key,
first_name varchar(100),
last_name varchar (100),
course integer
);