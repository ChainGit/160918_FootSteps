# ���֮ǰ������
drop database if exists test;

# ����test���ݿ�
create database test;

# ʹ��test���ݿ�
use test;

# ������login_user
create table login_user(
	id int not null primary key auto_increment,
	name char(10) not null,
	pass char(10) not null
);

# ����������login_user
insert into login_user
(name,pass)
values
('test','123'),
('����','����');


