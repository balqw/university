drop table if exists students cascade ;
create table students(
student_id integer primary key,
first_name varchar(100),
last_name varchar (100),
course integer
);