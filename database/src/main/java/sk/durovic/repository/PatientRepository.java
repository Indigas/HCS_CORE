package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

    //<S extends Patient> S save(S entity);
}
