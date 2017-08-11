DROP TABLE IF EXISTS s_user;

CREATE TABLE s_user (
  id   INT(5)   NOT NULL AUTO_INCREMENT,
  name CHAR(10) NOT NULL,
  age  INT(3)   NOT NULL,
  PRIMARY KEY (id)
);

INSERT s_user (name, age) VALUES ('小明x', 22), ('小刚o', 21), ('小红o', 20), ('小芳o', 19), ('小强p', 28);
