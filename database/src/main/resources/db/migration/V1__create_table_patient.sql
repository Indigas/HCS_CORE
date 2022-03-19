CREATE TABLE IF NOT EXISTS `Patient` (
  `id` VARCHAR(20) NOT NULL,
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `bloodGroup` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;