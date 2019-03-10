USE book_store;
CREATE TABLE `orders` (
  `id`              VARCHAR(100),
  `money`           DOUBLE,
  `receiverAddress` VARCHAR(255),
  `receiverName`    VARCHAR(20),
  `receiverPhone`   VARCHAR(20),
  `paystate`        INT(11),
  `ordertime`       TIMESTAMP,
  `user_id`         INT(11),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
