DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS teacher;

CREATE TABLE c_class (
  class_id   INT(5)   NOT NULL AUTO_INCREMENT,
  class_name CHAR(10) NOT NULL,
  teacher_id INT(5)   NOT NULL,
  PRIMARY KEY (class_id)
);

CREATE TABLE c_teacher (
  teacher_id   INT(5)   NOT NULL AUTO_INCREMENT,
  teacher_name CHAR(10) NOT NULL,
  PRIMARY KEY (teacher_id)
);

CREATE TABLE c_student (
  student_id   INT(5)   NOT NULL AUTO_INCREMENT,
  student_name CHAR(10) NOT NULL,
  student_age  INT(3)   NOT NULL,
  class_id     INT(5)   NOT NULL,
  PRIMARY KEY (student_id)
);

ALTER TABLE c_class
  ADD CONSTRAINT fk_c_teacher_id FOREIGN KEY (teacher_id) REFERENCES c_teacher (teacher_id);

ALTER TABLE c_student
  ADD CONSTRAINT fk_c_class_id FOREIGN KEY (class_id) REFERENCES c_class (class_id);

INSERT c_teacher (teacher_id, teacher_name) VALUES (1, '杨老师'), (2, '张老师');

INSERT c_class (class_id, class_name, teacher_id) VALUES (1, '二年级一班', 1), (2, '二年级二班', 2);

INSERT c_student (student_id, student_name, student_age, class_id)
VALUES (1, '小红', 22, 1), (2, '小刚', 21, 1), (3, '小芳', 22, 2), (4, '小强', 23, 2);
