USE book_store;
CREATE TABLE `user` (
  `id`         INT(11)     AUTO_INCREMENT,
  `username`   VARCHAR(20) UNIQUE,
  `PASSWORD`   VARCHAR(20),
  `gender`     VARCHAR(10),
  `email`      VARCHAR(50),
  `telephone`  VARCHAR(20),
  `introduce`  VARCHAR(100),
  `activeCode` VARCHAR(50),
  `state`      INT(11),
  `role`       VARCHAR(10) DEFAULT '普通用户',
  `registTime` TIMESTAMP,
  PRIMARY KEY (`id`)
);