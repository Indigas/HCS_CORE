package sk.durovic.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.durovic.exception.OperationNotSupported;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseRepository;

import java.util.List;
import java.util.Optional;

public abstract class PatientDiagnoseService
        extends EntityService<Patient_Diagnose, String, JpaRepository<Patient_Diagnose, String>> {

    protected PatientDiagnoseRepository repo;

    abstract public List<Patient_Diagnose> getDiagnosesByPatientId(String id);

    @Override
    public Patient_Diagnose save(Patient_Diagnose object) {
        throw new OperationNotSupported("Not allowed on View table");
    }

    @Override
    public void delete(Patient_Diagnose object) {
        throw new OperationNotSupported("Not allowed on View table");
    }

    @Override
    public void deleteById(String s) {
        throw new OperationNotSupported("Not allowed on View table");
    }

    @Override
    public Optional<Patient_Diagnose> load(String s) {
        throw new OperationNotSupported("Not allowed on View table");
    }
}
