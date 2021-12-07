CREATE TABLE employee
(
    employee_id INT(10)     not null auto_increment,
    number      VARCHAR(11) NOT NULL,
    phone       VARCHAR(11) NOT NULL,
    name        VARCHAR(10) NOT NULL,
    password    VARCHAR(18) NOT NULL,
    email       VARCHAR(30),
    gender      INT(1),
    type        INT(1)      NOT NULL,
    state       INT(1)      NOT NULL DEFAULT 0,
    PRIMARY KEY (employee_id)
) DEFAULT CHARSET = utf8;

CREATE TABLE customer
(
    customer_id INT(10)      not null auto_increment,
    phone       VARCHAR(11)      NOT NULL,
    password    VARCHAR(18)  NOT NULL,
    name        VARCHAR(10)  NOT NULL,
    email       VARCHAR(30)  NOT NULL,
    gender      INT(1)       NOT NULL,
    address     VARCHAR(100) NOT NULL,
    PRIMARY KEY (customer_id)
) DEFAULT CHARSET = utf8;

CREATE TABLE admin
(
    admin_id INT(10)     not null auto_increment,
    number   VARCHAR(11) NOT NULL,
    name     VARCHAR(10) NOT NULL,
    password VARCHAR(18) NOT NULL,
    PRIMARY KEY (admin_id)
) DEFAULT CHARSET = utf8;

CREATE TABLE product
(
    product_id   int(11)        NOT NULL AUTO_INCREMENT,
    name         varchar(255)   NOT NULL,
    price        decimal(10, 0) NOT NULL,
    image_url    varchar(255)   NOT NULL,
    category     varchar(255)   NOT NULL,
    introduction varchar(255)   NOT NULL,
    state        int(1)         NOT NULL DEFAULT 0,
    PRIMARY KEY (product_id)
) DEFAULT CHARSET = utf8;

CREATE TABLE ingredients
(
    ingredients_id int(11)        NOT NULL AUTO_INCREMENT,
    name           varchar(255)   NOT NULL,
    price          decimal(10, 0) NOT NULL,
    merchant       varchar(255)   NOT NULL,
    category       varchar(255)   NOT NULL,
    introduction   varchar(255)   NOT NULL,
    PRIMARY KEY (ingredients_id)
) DEFAULT CHARSET = utf8;

CREATE TABLE ingredients_list
(
    ingredients_id     int(11) NOT NULL,
    product_id         int(11) NOT NULL,
    ingredients_number int(2),
    PRIMARY KEY (ingredients_id, product_id),
    CONSTRAINT `fkey1` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`ingredients_id`),
    CONSTRAINT `fkey2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE product_order
(
    product_order_id int(11)          NOT NULL AUTO_INCREMENT,
    customer_id      INT(10)  NOT NULL,
    price            decimal(10, 0)   NOT NULL,
    order_date       datetime         NOT NULL,
    PRIMARY KEY (product_order_id),
    CONSTRAINT `fkey3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE order_detail
(
    product_order_id int(11)         NOT NULL,
    product_id       int(11)         NOT NULL,
    number           int(2)  NOT NULL,
    PRIMARY KEY (product_order_id, product_id),
    CONSTRAINT `fkey4` FOREIGN KEY (`product_order_id`) REFERENCES `product_order` (`product_order_id`),
    CONSTRAINT `fkey5` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE suggestion
(
    suggestion_id int(11)      NOT NULL AUTO_INCREMENT,
    content       VARCHAR(500) NOT NULL,
    suggest_time  datetime     NOT NULL,
    customer_id   INT(10)      NOT NULL,
    PRIMARY KEY (suggestion_id),
    CONSTRAINT `fkey6` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) DEFAULT CHARSET = utf8;


CREATE TABLE production
(
    production_id   int(11) NOT NULL AUTO_INCREMENT,
    product_id      int(11) NOT NULL,
    admin_id        int(10) NOT NULL,
    number          INT(10) NOT NULL,
    production_time datetime,
    PRIMARY KEY (production_id),
    CONSTRAINT `fkey7` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
    CONSTRAINT `fkey8` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE purchase_ingredients
(
    purchase_ingredients_id int(11)          NOT NULL AUTO_INCREMENT,
    ingredients_id          int(11)          NOT NULL,
    admin_id                int(10)  NOT NULL,
    number                  INT(10)  NOT NULL,
    purchase_time           datetime,
    PRIMARY KEY (purchase_ingredients_id),
    CONSTRAINT `fkey9` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredients` (`ingredients_id`),
    CONSTRAINT `fkey10` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) DEFAULT CHARSET = utf8;
drop table if exists feedback;
CREATE TABLE feedback
(
    suggestion_id int(11)          NOT NULL,
    employee_id      int(10)  NOT NULL,
    feedback_time datetime         NOT NULL,
    state int(1) NOT NULL,
    content       varchar(255)     NOT NULL DEFAULT 0,
    PRIMARY KEY (suggestion_id, employee_id),
    CONSTRAINT `fkey11` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
    CONSTRAINT `fkey12` FOREIGN KEY (`suggestion_id`) REFERENCES `suggestion` (`suggestion_id`)
) DEFAULT CHARSET = utf8;

CREATE TABLE manage_employee
(
    manage_employee_id int(11)          NOT NULL AUTO_INCREMENT,
    employee_id        INT(10)  not null,
    admin_id           int(10)  NOT NULL,
    manage_type        INT(1)           NOT NULL,
    manage_time        datetime,
    PRIMARY KEY (manage_employee_id),
    CONSTRAINT `fkey13` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
    CONSTRAINT `fkey14` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) DEFAULT CHARSET = utf8;

Drop table if exists manage_order;
CREATE TABLE manage_order
(
    manage_order_id  int(11)          NOT NULL AUTO_INCREMENT,
    product_order_id int(11)          NOT NULL,
    order_status     int(1)           NOT NULL DEFAULT 0,
    payment_status   int(1)           NOT NULL DEFAULT 0,
    manage_time      datetime,
    PRIMARY KEY (manage_order_id),
    CONSTRAINT `fkey16` FOREIGN KEY (`product_order_id`) REFERENCES `product_order` (`product_order_id`)
) DEFAULT CHARSET = utf8;

