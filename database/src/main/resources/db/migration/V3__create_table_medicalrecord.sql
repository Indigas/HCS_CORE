CREATE TABLE IF NOT EXISTS `MedicalRecord` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(15) NULL,
  `text` TEXT NULL,
  `diagnose_id` BIGINT NULL,
  `patient_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `diagnose_idx` (`diagnose_id` ASC) VISIBLE,
  INDEX `fk_patient_idx` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `fk_diagnose`
    FOREIGN KEY (`diagnose_id`)
    REFERENCES `Diagnose` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_patient_medicalrecord`
    FOREIGN KEY (`patient_id`)
    REFERENCES `Patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;