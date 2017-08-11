DROP TABLE IF EXISTS c_user;

CREATE TABLE c_user (
  id   INT(5)   NOT NULL AUTO_INCREMENT,
  name CHAR(10) NOT NULL,
  age  INT(3)   NOT NULL,
  PRIMARY KEY (id)
);

INSERT c_user (name, age) VALUES ('小明x', 22), ('小刚o', 21), ('小红o', 20), ('小芳o', 19), ('小强p', 28);

DELIMITER $
CREATE PROCEDURE count_user_by_type(IN type INT, OUT amount INT)
  BEGIN
    IF type = 0
    THEN
      SELECT count(*)
      FROM c_user
      WHERE c_user.age < 25
      INTO amount;
    ELSE
      SELECT count(*)
      FROM c_user
      WHERE c_user.age >= 25
      INTO amount;
    END IF;
  END $