CREATE TABLE product
(
    product_id   int(11) NOT NULL AUTO_INCREMENT,
    name         varchar(255)   NOT NULL,
    price        decimal(10, 0) NOT NULL,
    image_url    varchar(255)   NOT NULL,
    category     varchar(255)   NOT NULL,
    introduction varchar(255)   NOT NULL,
    state        int(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (product_id)
) DEFAULT CHARSET = utf8;
