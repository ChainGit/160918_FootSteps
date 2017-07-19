DROP DATABASE IF EXISTS test;

CREATE DATABASE test;

USE test;

CREATE TABLE department (
  id              BIGINT(20)  NOT NULL AUTO_INCREMENT,
  department_id   VARCHAR(20) NOT NULL,
  department_name VARCHAR(20) NOT NULL,
  UNIQUE (department_id(20)),
  PRIMARY KEY (id)
);

CREATE TABLE employee (
  id                   BIGINT(20)  NOT NULL AUTO_INCREMENT,
  employee_id          VARCHAR(20) NOT NULL,
  employee_name        VARCHAR(20) NOT NULL,
  employee_email       VARCHAR(20),
  employee_age         TINYINT,
  employee_birth       DATE,
  employee_create_time DATE        NOT NULL,
  department_id        BIGINT,
  UNIQUE (employee_id(20)),
  CONSTRAINT PRIMARY KEY (id),
  FOREIGN KEY (department_id) REFERENCES department (id)
);

INSERT INTO
  department
  (department_id, department_name)
VALUES
  ("DEPT-00214", "技术部"),
  ("DEPT-00216", "人事部"),
  ("DEPT-00234", "销售部"),
  ("DEPT-00114", "产品部");

INSERT INTO
  employee
  (employee_id, employee_name, employee_email, employee_age, employee_birth, employee_create_time, department_id)
VALUES
  ("EMPL-000234", "小明", "xiaoming@test.com", 20, '1996-02-22', '2016-12-11', NULL),
  ("EMPL-000224", "小刚", "xiaogang@test.com", 22, '1997-11-22', '2016-08-11', NULL),
  ("EMPL-000434", "小芳", "xiaofang@test.com", 23, '1991-02-18', '2016-12-28', NULL),
  ("EMPL-000111", "小红", "xiaohong@test.com", 21, '1996-04-22', '2015-03-14', NULL),
  ("EMPL-000124", "小强", "xiaoqiang@test.com", 24, '1996-02-22', '2016-05-21', NULL),
  ("EMPL-000194", "小美", "xiaomei@test.com", 19, '1994-10-24', '2016-10-17', NULL);
