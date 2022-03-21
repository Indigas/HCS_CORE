package sk.durovic.repository;

import org.springframework.stereotype.Repository;
import sk.durovic.model.Patient_Diagnose;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientDiagnoseRepository<T, ID> extends PatientDiagnoseReadOnlyRepository<T, ID> {
    Optional<List<T>> findByPatientId(ID patientId);
}
