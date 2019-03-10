USE book_store;
CREATE TABLE `products` (
  `id`          VARCHAR(100),
  `name`        VARCHAR(40),
  `price`       DOUBLE,
  `category`    VARCHAR(40),
  `pnum`        INT(11),
  `imgurl`      VARCHAR(100),
  `description` VARCHAR(255),
  PRIMARY KEY (`id`)
);