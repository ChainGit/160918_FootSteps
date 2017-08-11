DROP TABLE IF EXISTS c_order;

CREATE TABLE c_order (
  order_id     INT(5)   NOT NULL AUTO_INCREMENT,
  order_name   CHAR(10) NOT NULL,
  order_amount INT(5)   NOT NULL,
  PRIMARY KEY (order_id)
);

INSERT INTO c_order (order_name, order_amount) VALUES ('order_1', 100), ('order_2', 50);