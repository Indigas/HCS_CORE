CREATE TABLE IF NOT EXISTS `Disease` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `patient_id` VARCHAR(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `patient_id_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_patient_disease`
    FOREIGN KEY (`patient_id`)
    REFERENCES `Patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;