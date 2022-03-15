package sk.durovic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.MedicalRecord;


public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {
}
