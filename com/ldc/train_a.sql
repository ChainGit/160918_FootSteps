# 设有成绩表stu如下：
# 姓名     科目    分数
# (name) (lesson) (score)
# 张三     数学     90
# 张三     语文     50
# 李四     语文     55
# 张三     地理     40
# 李四     政治     45
# 王五     政治     30
# 试查询两门以及两门以上不及格的同学的平均分

# 分析：
#      1、两门以及两门以上
#      2、不及格
#      3、所有科目的平均分

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
( '张三','数学',90),
( '张三','语文',50),
( '李四','语文',55),
( '张三','地理',40),
( '李四','政治',45),
( '王五','政治',30);

select * from stu;

## 做法：

# 做法一（普通查询）：
select name,avg(score) as pinjun ,sum(score < 60) as guake from stu group by name having guake > 1; 

# 结果：
# +--------+---------+-------+
# | name   | pinjun  | guake |
# +--------+---------+-------+
# | 张三   | 60.0000 |     2 |
# | 李四   | 50.0000 |     2 |
# +--------+---------+-------+
# 2 rows in set (0.00 sec)


# 做法二（子查询 where + from）：
select name,avg(score) as pinjun 
from stu 
where name in 
( select name from ( select name, count(score) as guake from stu where score < 60 group by name having guake > 1 ) as tmp ) 
group by name;

# 结果： 
# +--------+---------+
# | name   | pinjun  |
# +--------+---------+
# | 张三   | 60.0000 |
# | 李四   | 50.0000 |
# +--------+---------+
# 2 rows in set (0.00 sec)

