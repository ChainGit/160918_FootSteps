DROP DATABASE IF EXISTS user;

CREATE TABLE user (
  id   INT(5)   NOT NULL AUTO_INCREMENT,
  name CHAR(10) NOT NULL,
  age  INT(3)   NOT NULL,
  PRIMARY KEY (id)
);

INSERT user (name, age) VALUES ('小明', 22), ('小刚', 21);