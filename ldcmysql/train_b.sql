# �����Ӷ�����
create table fmatch (
fid int not null primary key auto_increment,
hid int not null default 0,
gid int not null default 0,
res char(5) not null default '',
tim date not null
);

create table fteam (
tid int not null primary key,
nam varchar(10) not null
);

insert into fmatch 
(hid,gid,res,tim)
values
(1,3,'2:0','2017-02-02'),
(2,1,'2:1','2016-05-22'),
(2,4,'2:3','2016-11-22'),
(1,2,'1:0','2015-12-21'),
(3,4,'1:1','2015-09-18');

insert into fteam
(tid,nam)
values
(0,'δ֪'),
(1,'ʢ��'),
(2,'����'),
(3,'����'),
(4,'ս��');

# ���� fmatch
desc fmatch;

# +-------+---------+------+-----+---------+----------------+
# | Field | Type    | Null | Key | Default | Extra          |
# +-------+---------+------+-----+---------+----------------+
# | fid   | int(11) | NO   | PRI | NULL    | auto_increment |
# | hid   | int(11) | NO   |     | 0       |                |
# | gid   | int(11) | NO   |     | 0       |                |
# | res   | char(5) | NO   |     |         |                |
# | tim   | date    | NO   |     | NULL    |                |
# +-------+---------+------+-----+---------+----------------+

select * from fmatch;

# +-----+-----+-----+-----+------------+
# | fid | hid | gid | res | tim        |
# +-----+-----+-----+-----+------------+
# |   1 |   1 |   3 | 2:0 | 2017-02-02 |
# |   2 |   2 |   1 | 2:1 | 2016-05-22 |
# |   3 |   2 |   4 | 2:3 | 2016-11-22 |
# |   4 |   1 |   2 | 1:0 | 2015-12-21 |
# |   5 |   3 |   4 | 1:1 | 2015-09-18 |
# +-----+-----+-----+-----+------------+

# �������� fteam
desc fteam;

# +-------+-------------+------+-----+---------+-------+
# | Field | Type        | Null | Key | Default | Extra |
# +-------+-------------+------+-----+---------+-------+
# | tid   | int(11)     | NO   | PRI | NULL    |       |
# | nam   | varchar(10) | NO   |     | NULL    |       |
# +-------+-------------+------+-----+---------+-------+

select * from fteam;

# +-----+--------+
# | tid | nam    |
# +-----+--------+
# |   0 | δ֪   |
# |   1 | ʢ��   |
# |   2 | ����   |
# |   3 | ����   |
# |   4 | ս��   |
# +-----+--------+

# ��Ҫ��������¸�ʽ��
# �磺
# ʢ�� 1:0 ���� 2015-12-21
# ���󣺰���ʽ�����2016���ڼ�ٰ�ı���


select fid,tmp_host.nam,res,tmp_guest.nam,tim
from fmatch
left join
fteam as tmp_host
on tmp_host.tid = fmatch.hid 
left join
fteam as tmp_guest 
on tmp_guest.tid = fmatch.gid
where tim between '2016-01-01' and '2016-12-31'
order by fid;

# �����
# +-----+--------+-----+--------+------------+
# | fid | nam    | res | nam    | tim        |
# +-----+--------+-----+--------+------------+
# |   2 | ����   | 2:1 | ʢ��   | 2016-05-22 |
# |   3 | ����   | 2:3 | ս��   | 2016-11-22 |
# +-----+--------+-----+--------+------------+






