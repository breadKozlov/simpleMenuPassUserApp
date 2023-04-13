--create database user_product
--create schema public

create table "public".products(
id serial primary key ,
name_of_product varchar(120) not null,
description varchar(300) not null
)

create table "public".users(
name_of_user varchar(50) not null,
age_user int not null,
email varchar(30) not null,
login varchar(30) not null primary key,
password varchar(30) not null
)

insert into "public".products (name_of_product,description)
values ('Apple','Iphone'),
('Samsung','Galaxy')

insert into "public".users(name_of_user,age_user,email,login,password)
values ('Pasha',33,'killersarequiet2018@gmail.com','breadKozlov','password'),
('Sasha',13,'gopnik112@mail.ru','Sasha112','123456789')