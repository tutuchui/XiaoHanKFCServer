CREATE TABLE customer
(
    customer_id INT(10) UNSIGNED not null auto_increment,
    phone       int(11) NOT NULL,
    password    VARCHAR(18)  NOT NULL,
    name        VARCHAR(10)  NOT NULL,
    email       VARCHAR(30)  NOT NULL,
    gender      INT(1) NOT NULL,
    address     VARCHAR(100) NOT NULL,
    PRIMARY KEY (customer_id)
)DEFAULT CHARSET = utf8;
