drop database if exists test;

create database test;

use test;

create table city (
	city_id int not null,
	city_name char(10) not null
);

create table department (
	depart_id int not null,
	depart_name char(10) not null,
	city_id int not null
);

create table employee (
	employ_id int not null,
	employ_name char(10) not null,
	depart_id int not null
);

insert into city
values
(101,'AAA'),
(102,'BB'),
(103,'CCCC'),
(104,'DDD'),
(105,'EEEE');

insert into department
values
(20101,'aaa',101),
(20010,'bb',102),
(20018,'cccc',101),
(20131,'ddd',103),
(20012,'eeee',103),
(20182,'ff',102),
(20412,'g',101),
(20722,'hh',105),
(20932,'ii',102),
(21642,'jjjj',101),
(22632,'kkk',103),
(24642,'ll',102),
(25662,'mmm',101);

insert into employee
values
(80010329,'tom1',20101),
(80019153,'tom23',20101),
(80230329,'tom2',20010),
(80130429,'tom3',21642),
(80410329,'tom5',20101),
(80310529,'tom7',20010),
(80010326,'tom9',20101),
(80012292,'tom28',20101),
(80450322,'tom4',20101),
(80039228,'tom24',20101),
(80020229,'tom10',20101),
(83500329,'tom12',20101),
(80012394,'tom21',20101),
(80072829,'tom13',20010),
(80054293,'tom14',20101),
(82015329,'tom11',20018),
(80510229,'tom6',20131),
(80034543,'tom15',20018),
(80029483,'tom18',21642),
(80012950,'tom19',20101),
(80096268,'tom16',25662),
(82359392,'tom20',20018),
(80023940,'tom22',20722),
(80182945,'tom25',25662),
(80510629,'tom8',20018),
(80293742,'tom26',20010),
(80023322,'tom27',20722),
(80019729,'tom17',20131);

