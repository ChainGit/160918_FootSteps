/*
含义：又称多表查询，当查询的字段来自于多个表时，就会用到连接查询

笛卡尔乘积现象：表1 有m行，表2有n行，结果=m*n行

发生原因：没有有效的连接条件
如何避免：添加有效的连接条件

分类：
	按年代分类：
	    sql92标准：仅仅支持内连接
	    sql99标准【推荐】：支持内连接 + 外连接（左外和右外） + 交叉连接
	
	按功能分类：
		内连接：
			等值连接
			非等值连接
			自连接
		外连接：
			左外连接
			右外连接
			全外连接
		交叉连接
*/
USE employees;

# sql92标准：内连接（仅支持）

## 笛卡尔积（没有有效的连接条件）

SELECT * FROM departments d,employees e;

## 等值连接
 SELECT
  *
FROM
  salaries s,
  employees e
WHERE e.`emp_no` = s.`emp_no`
  AND s.`to_date` = '9999-01-01';

## 不等值连接
 SELECT
  *
FROM
  salaries s,
  employees e
WHERE e.`emp_no` = s.`emp_no`
  AND s.`to_date` = '9999-01-01'
  AND s.`salary` BETWEEN 80000 AND 100000;
  
## 自连接
 SELECT
  *
FROM
  salaries s1,
  salaries s2
WHERE s1.`from_date` = s2.`to_date`;


## 练习：查询工资最高的在职经理所在的部门的工资最低的在职员工的姓名，职位，工资。

/*
嵌套双层查询可以解决 LIMIT & SUBQUERY
*/

### 1.查询出所有的经理的员工号

SELECT emp_no FROM dept_manager dm;

### 2.查出工资最高的经理的员工号
 SELECT
  emp_no,
  salary,
  from_date,
  to_date
FROM
  salaries s
WHERE s.`to_date` = '9999-01-01'
  AND s.emp_no IN
  (SELECT
    emp_no
  FROM
    dept_manager dm)
ORDER BY s.salary DESC
LIMIT 1;

### 3.查出工资最高的经理的部门号
 SELECT
  dm.dept_no
FROM
  dept_manager dm
WHERE dm.`emp_no` IN
  (SELECT t.emp_no FROM (SELECT
  emp_no,
  salary,
  from_date,
  to_date
FROM
  salaries s
WHERE s.`to_date` = '9999-01-01'
  AND s.emp_no IN
  (SELECT
    emp_no
  FROM
    dept_manager dm)
ORDER BY s.salary DESC
LIMIT 1) AS t);
        
### 4.由3结果查询该部门内所有员工号
 SELECT
  de.`emp_no`
FROM
  dept_emp de
WHERE de.`dept_no` IN
  ( SELECT
  dm.dept_no
FROM
  dept_manager dm
WHERE dm.`emp_no` IN
  (SELECT t.emp_no FROM (SELECT
  emp_no,
  salary,
  from_date,
  to_date
FROM
  salaries s
WHERE s.`to_date` = '9999-01-01'
  AND s.emp_no IN
  (SELECT
    emp_no
  FROM
    dept_manager dm)
ORDER BY s.salary DESC
LIMIT 1) AS t));

          
### 5.由4结果查询工资最低的员工号
SELECT s.`emp_no`,s.`salary` FROM salaries s WHERE s.`emp_no` IN (SELECT
  de.`emp_no`
FROM
  dept_emp de
WHERE de.`dept_no` IN
  ( SELECT
  dm.dept_no
FROM
  dept_manager dm
WHERE dm.`emp_no` IN
  (SELECT t.emp_no FROM (SELECT
  emp_no,
  salary,
  from_date,
  to_date
FROM
  salaries s
WHERE s.`to_date` = '9999-01-01'
  AND s.emp_no IN
  (SELECT
    emp_no
  FROM
    dept_manager dm)
ORDER BY s.salary DESC
LIMIT 1) AS t))) ORDER BY s.`salary` LIMIT 1;

### 6.由5结果查询出所需信息
 SELECT
  s.`emp_no`,
  CONCAT(
    e.`first_name`,
    ' ',
    e.`last_name`
  ) AS 'name',
  t.`title`,
  s.`salary`
FROM
  employees e,
  titles t,
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
        dm.dept_no
      FROM
        dept_manager dm
      WHERE dm.`emp_no` IN
        (SELECT
          t.emp_no
        FROM
          (SELECT
            emp_no,
            salary,
            from_date,
            to_date
          FROM
            salaries s
          WHERE s.`to_date` = '9999-01-01'
            AND s.emp_no IN
            (SELECT
              emp_no
            FROM
              dept_manager dm)
          ORDER BY s.salary DESC
          LIMIT 1) AS t)))
  ORDER BY s.`salary`
  LIMIT 1) AS s
WHERE e.`emp_no` = t.`emp_no`
  AND t.`to_date` = '9999-01-01'
  AND s.`emp_no` = e.`emp_no`;

