/*
sql99语法：
	select 查询列表
	from 表1 别名 
	【连接类型】join 
	表2 别名 
	on 连接条件
	【where 筛选条件】
	【group by 分组】
	【having 筛选条件】
	【order by 排序列表】
	
分类：
	内连接（★）：【inner】 join
	外连接
		左外(★):left 【outer】 join
		右外(★)：right 【outer】 join
		全外：full【outer】join
	交叉连接：cross join
*/

USE employees;

SELECT * FROM departments;

SELECT * FROM dept_emp;

# 内连接（相交部分）
/*
语法：
	select 查询列表
	from 表1 别名
	【inner】 join 表2 别名
	on 连接条件;

分类：
	等值
	非等值
	自连接

特点：
	①添加排序、分组、筛选
	②inner可以省略
	③与sql92相比，筛选条件放在where后面，连接条件放在on后面，提高分离性，便于阅读
	④inner join连接和sql92语法中的连接效果是一样的，都是查询多表的【交集】
*/

## 笛卡尔积

/*
cross join是纯粹的笛卡尔积，理论上不应该支持on语法，但是MySQL在这方面做的比较奇怪。
cross join也可以添加on条件。
所以在mysql中，cross join和inner join可以等同，相互替代。
*/

SELECT * FROM employees,departments;

SELECT * FROM employees INNER JOIN departments;

SELECT * FROM employees JOIN departments;

## 等值连接
 SELECT
  *
FROM
  salaries s
  JOIN employees e
    ON e.`emp_no` = s.`emp_no`
    AND s.`to_date` = '9999-01-01'
WHERE e.`first_name` = 'Parto';

# 外连接
 /*
 应用场景：用于查询一个表中有，另一个表没有的记录
 
 特点：
	1、外连接的查询结果为主表中的所有记录
		如果从表中有和它匹配的，则显示匹配的值
		如果从表中没有和它匹配的，则显示null
		【外连接查询结果 = 内连接结果 + 主表中有而从表没有的记录】
	2、左外连接，left join左边的是主表
	   右外连接，right join右边的是主表
	3、左外和右外交换两个表的顺序，可以实现同样的效果 
	4、【全外连接 = 内连接的结果 + 表1中有但表2没有的 + 表2中有但表1没有的】
 */
## 左外连接
 SELECT
  *
FROM
  departments d
  LEFT OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01';
    
## 右外连接
 SELECT
  *
FROM
  dept_emp de
  RIGHT OUTER JOIN departments d
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01';
    
## 左外连接（不包含相交部分）
 SELECT
  *
FROM
  departments d
  LEFT OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01'
    WHERE de.`dept_no` IS NULL;
    
## 右外连接（不包含相交部分）
 SELECT
  *
FROM
  dept_emp de
  RIGHT OUTER JOIN departments d
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01'
    WHERE de.`dept_no` IS NULL;

## 不包含相交部分
 (SELECT
  *
FROM
  departments d
  LEFT OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01'
WHERE de.`dept_no` IS NULL)
UNION
(SELECT
  *
FROM
  departments d
  RIGHT OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01'
WHERE d.`dept_no` IS NULL);

## 全连接（包含三部分）
 /*
union默认是去重的，如果不想去重，使用union all
*/
(SELECT
  *
FROM
  departments d
  LEFT OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01')
UNION
(SELECT
  *
FROM
  departments d
  RIGHT
  OUTER JOIN dept_emp de
    ON d.`dept_no` = de.`dept_no`
    AND de.`to_date` = '9999-01-01');
    
# 练习：查询工资最高的在职经理所在的部门的工资最低的在职员工的姓名，职位，工资。
 ## 1.查出所有的在职经理
 SELECT
  dm.`emp_no`,
  dm.`dept_no`
FROM
  dept_manager dm WHERE dm.`to_date`='9999-01-01';
  
## 2.查出1中的经理中工资最高的
 SELECT
  s.`emp_no`,
  s.`salary`
FROM
  salaries s
WHERE s.`emp_no` IN
  (SELECT
    dm.`emp_no`
  FROM
    dept_manager dm
  WHERE dm.`to_date` = '9999-01-01')
ORDER BY s.`salary` DESC
LIMIT 1;

## 3.查出2中的经理所在的部门
 SELECT
  dm.`dept_no`
FROM
  dept_manager dm
WHERE dm.`emp_no` IN
  (SELECT
    t.`emp_no`
  FROM
    (SELECT
      s.`emp_no`,
      s.`salary`
    FROM
      salaries s
    WHERE s.`emp_no` IN
      (SELECT
        dm.`emp_no`
      FROM
        dept_manager dm
      WHERE dm.`to_date` = '9999-01-01')
    ORDER BY s.`salary` DESC
    LIMIT 1) AS t);

## 4.查出3中部门的所有在职员工
 SELECT
  de.`emp_no`
FROM
  dept_emp de
WHERE de.`dept_no` IN
  (SELECT
    dm.`dept_no`
  FROM
    dept_manager dm
  WHERE dm.`emp_no` IN
    (SELECT
      t.`emp_no`
    FROM
      (SELECT
        s.`emp_no`,
        s.`salary`
      FROM
        salaries s
      WHERE s.`emp_no` IN
        (SELECT
          dm.`emp_no`
        FROM
          dept_manager dm
        WHERE dm.`to_date` = '9999-01-01')
      ORDER BY s.`salary` DESC
      LIMIT 1) AS t)) AND de.`to_date`='9999-01-01';
      
## 5.查询4中所有员工的工资最低的员工号
 SELECT
  s.`emp_no`,
  s.`salary`
FROM
  salaries s
WHERE s.`emp_no` IN
  (SELECT
    de.`emp_no`
  FROM
    dept_emp de
  WHERE de.`dept_no` IN
    (SELECT
      dm.`dept_no`
    FROM
      dept_manager dm
    WHERE dm.`emp_no` IN
      (SELECT
        t.`emp_no`
      FROM
        (SELECT
          s.`emp_no`,
          s.`salary`
        FROM
          salaries s
        WHERE s.`emp_no` IN
          (SELECT
            dm.`emp_no`
          FROM
            dept_manager dm
          WHERE dm.`to_date` = '9999-01-01')
          AND s.`to_date` = '9999-01-01'
        ORDER BY s.`salary` DESC
        LIMIT 1) AS t))
    AND de.`to_date` = '9999-01-01')
  AND s.`to_date` = '9999-01-01'
ORDER BY s.`salary`
LIMIT 1;

      
## 6.查询5中的员工所需信息
 SELECT
  e.`emp_no`,
  CONCAT(
    e.`first_name`,
    ' ',
    e.`last_name`
  ) AS 'name',
  p.`salary`,
  t.`title`
FROM
  employees e
  JOIN titles t
    ON e.`emp_no` = t.`emp_no`
    AND t.`to_date` = '9999-01-01'
  JOIN
    (SELECT
  s.`emp_no`,
  s.`salary`
FROM
  salaries s
WHERE s.`emp_no` IN
  (SELECT
    de.`emp_no`
  FROM
    dept_emp de
  WHERE de.`dept_no` IN
    (SELECT
      dm.`dept_no`
    FROM
      dept_manager dm
    WHERE dm.`emp_no` IN
      (SELECT
        t.`emp_no`
      FROM
        (SELECT
          s.`emp_no`,
          s.`salary`
        FROM
          salaries s
        WHERE s.`emp_no` IN
          (SELECT
            dm.`emp_no`
          FROM
            dept_manager dm
          WHERE dm.`to_date` = '9999-01-01')
          AND s.`to_date` = '9999-01-01'
        ORDER BY s.`salary` DESC
        LIMIT 1) AS t))
    AND de.`to_date` = '9999-01-01')
  AND s.`to_date` = '9999-01-01'
ORDER BY s.`salary`
LIMIT 1) AS p
    ON p.`emp_no` = e.`emp_no`;

