CREATE TABLE `pet_order` (
  `id`                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name`         VARCHAR(20)  NOT NULL,
  `province`     VARCHAR(20)  NOT NULL,
  `city`     VARCHAR(20)  NOT NULL,
  `area`     VARCHAR(20)  NOT NULL,
  `street`     VARCHAR(30)  NOT NULL,
  `more_details`     VARCHAR(150)  NOT NULL,
  `price`     BIGINT  NOT NULL,
  `amount`     BIGINT  NOT NULL,
  `description`     VARCHAR(60)  NOT NULL,
  `brand`     VARCHAR(20)  NOT NULL
);