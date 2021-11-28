CREATE TABLE employee
(
    employee_id INT(10) UNSIGNED not null auto_increment,
    number      VARCHAR(11) NOT NULL,
    phone       VARCHAR(11) NOT NULL,
    name        VARCHAR(10) NOT NULL,
    password    VARCHAR(18) NOT NULL,
    email       VARCHAR(30),
    gender      INT(1),
    type        INT(1) NOT NULL,
    state       INT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (employee_id)
)DEFAULT CHARSET = utf8;
