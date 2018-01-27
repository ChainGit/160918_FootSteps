/*
子查询含义：
	出现在其他语句中的select语句，称为子查询或内查询
	外部的查询语句，称为主查询或外查询

分类：
	按子查询出现的位置：
		select后面：
			仅仅支持标量子查询
		from后面：
			支持表子查询
		where或having后面：★
			标量子查询（单行） √
			列子查询  （多行） √
			行子查询
		exists后面（相关子查询）
			表子查询
	
	按结果集的行列数不同：
		标量子查询（结果集只有一行一列）
		列子查询（结果集只有一列多行）
		行子查询（结果集有一行多列）
		表子查询（结果集一般为多行多列）
*/
# where或having后面
 /*
1、标量子查询（单行子查询）
2、列子查询（多行子查询）
3、行子查询（多列多行）

特点：
①子查询放在小括号内
②子查询一般放在条件的右侧
③标量子查询，一般搭配着单行操作符使用
	比如：> < >= <= = <>
列子查询，一般搭配着多行操作符使用
	比如：in、any/some、all
④子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果
*/

## 行子查询（结果集一行多列或多行多列）
### 案例：查询员工编号最小并且工资最高的员工信息
SELECT * 
FROM employees
WHERE (employee_id,salary)=(
	SELECT MIN(employee_id),MAX(salary)
	FROM employees
);

# select后面
 /*
仅仅支持标量子查询
*/
## 案例：查询每个部门的员工个数
 SELECT
  d.*,
  (SELECT
    COUNT(*)
  FROM
    employees e
  WHERE e.department_id = d.`department_id`) 个数
FROM
  departments d;
  
# from后面
 /*
将子查询结果充当一张表，【必须起别名】。
*/
# exists后面（相关子查询）
 /*
语法：
	exists(完整的查询语句)
结果：
	1或0
*/
## 案例：查询有员工的部门名
### in
 SELECT
  department_name
FROM
  departments d
WHERE d.`department_id` IN
  (SELECT
    department_id
  FROM
    employees) 

### exists
   SELECT
    department_name
  FROM
    departments d
  WHERE EXISTS
    (SELECT
      *
    FROM
      employees e
    WHERE d.`department_id` = e.`department_id`);

