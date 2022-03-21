package sk.durovic.repository;

import org.springframework.stereotype.Repository;
import sk.durovic.model.Patient_Diagnose;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDiagnoseRepository extends PatientDiagnoseReadOnlyRepository {
    Optional<List<Patient_Diagnose>> findByPatientId(String patientId);
}
