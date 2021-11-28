CREATE TABLE admin
(
    admin_id INT(10) UNSIGNED not null auto_increment,
    number   VARCHAR(11) NOT NULL,
    name     VARCHAR(10) NOT NULL,
    password VARCHAR(18) NOT NULL,
    PRIMARY KEY (admin_id)
)DEFAULT CHARSET = utf8;
