USE book_store;
CREATE TABLE `orderitem` (
  `order_id`   VARCHAR(100),
  `product_id` VARCHAR(100),
  `buynum`     INT(11),
  PRIMARY KEY (`order_id`, `product_id`),
  FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)