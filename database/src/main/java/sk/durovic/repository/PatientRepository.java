package sk.durovic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Patient;


public interface PatientRepository extends CrudRepository<Patient, String> {
}
