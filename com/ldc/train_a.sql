# ���гɼ���stu���£�
# ����     ��Ŀ    ����
# (name) (lesson) (score)
# ����     ��ѧ     90
# ����     ����     50
# ����     ����     55
# ����     ����     40
# ����     ����     45
# ����     ����     30
# �Բ�ѯ�����Լ��������ϲ������ͬѧ��ƽ����

# ������
#      1�������Լ���������
#      2��������
#      3�����п�Ŀ��ƽ����

create database test;

use test;

create table stu (
	name char(3) not null,
	lesson char(3) not null,
	score tinyint not null default 0
)charset = utf8;

insert into stu
(name,lesson,score)
values
( '����','��ѧ',90),
( '����','����',50),
( '����','����',55),
( '����','����',40),
( '����','����',45),
( '����','����',30);

select * from stu;

## ������

# ����һ����ͨ��ѯ����
select name,avg(score) as pinjun ,sum(score < 60) as guake from stu group by name having guake > 1; 

# �����
# +--------+---------+-------+
# | name   | pinjun  | guake |
# +--------+---------+-------+
# | ����   | 60.0000 |     2 |
# | ����   | 50.0000 |     2 |
# +--------+---------+-------+
# 2 rows in set (0.00 sec)


# ���������Ӳ�ѯ where + from����
select name,avg(score) as pinjun 
from stu 
where name in 
( select name from ( select name, count(score) as guake from stu where score < 60 group by name having guake > 1 ) as tmp ) 
group by name;

# ����� 
# +--------+---------+
# | name   | pinjun  |
# +--------+---------+
# | ����   | 60.0000 |
# | ����   | 50.0000 |
# +--------+---------+
# 2 rows in set (0.00 sec)

