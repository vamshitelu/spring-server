CREATE TABLE user(
id integer primary key not null,
user_name varchar(40) not null,
password varchar(40) not null,
first_name varchar(40),
last_name varchar(40),
email varchar(100) not null,
mobile varchar(20) );

CREATE SEQUENCE user_seq START 1;