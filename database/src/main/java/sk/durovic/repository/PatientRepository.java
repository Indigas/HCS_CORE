package sk.durovic.repository;

import org.hibernate.NonUniqueObjectException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Patient;


public interface PatientRepository extends CrudRepository<Patient, String> {

    <S extends Patient> S save(S entity);
}
