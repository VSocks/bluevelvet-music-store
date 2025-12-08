CREATE TABLE `db`.`product` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(30) NULL,
	`short_description` VARCHAR(50) NULL,
	`full_description` VARCHAR(100) NULL,
	`brand` VARCHAR(45) NULL,
	`category` VARCHAR(45) NULL,
	`list_price` DECIMAL(14,2) NULL,
	`discount` DECIMAL(14,2) NULL,
	`enabled` BIT NULL,
	`in_stock` BIT NULL,
	`creation_time` DATETIME NULL,
	`update_time` DATETIME NULL,
	`cost` DECIMAL(14,2) NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `db`.`box_dimension` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`length` FLOAT NOT NULL,
	`width` FLOAT NOT NULL
	`height` FLOAT NOT NULL,
	`weight` FLOAT NOT NULL,
	`product_id` INT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_PRODUCT_idx` (`product_id` ASC) VISIBLE,
	CONSTRAINT `FK_PRODUCT`
		FOREIGN KEY (`product_id`)
			REFERENCES `db`.`product` (`id`)
			ON DELETE CASCADE
			ON UPDATE CASCADE);

CREATE TABLE `db`.`product_detail` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45) NOT NULL,
	`value` VARCHAR(45) NOT NULL,
	`product_id` INT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_product_detail_idx` (`product_id` ASC) VISIBLE,
	CONSTRAINT `fk_product_detail`
		FOREIGN KEY (`product_id`)
		REFERENCES `db`.`product` (`id`)
		ON DELETE CASCADE
		ON UPDATE CASCADE);