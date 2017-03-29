# 清除之前的数据
drop database if exists test;

# 创建test数据库
create database test;

# 使用test数据库
use test;

# 创建表login_user
create table login_user(
	id int not null primary key auto_increment,
	name char(10) not null,
	pass char(10) not null
);

# 插入数据于login_user
insert into login_user
(name,pass)
values
('test','123'),
('测试','密码');


