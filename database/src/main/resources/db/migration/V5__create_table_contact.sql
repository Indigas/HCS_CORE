CREATE TABLE IF NOT EXISTS `Contact` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(255) NULL,
  `telephone` VARCHAR(45) NULL,
  `notes` VARCHAR(255) NULL,
  `patient_id` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_patient_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_patient_contact`
    FOREIGN KEY (`patient_id`)
    REFERENCES `Patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;