CREATE TABLE `payment` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `order_id`         VARCHAR(20)  NOT NULL,
  `payment_status`     VARCHAR(20)  NOT NULL
);