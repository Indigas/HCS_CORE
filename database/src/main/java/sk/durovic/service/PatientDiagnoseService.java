package sk.durovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseReadOnlyRepository;
import java.util.List;

public abstract class PatientDiagnoseService<R extends PatientDiagnoseReadOnlyRepository> {

    @Autowired
    protected R repo;

    abstract public List<Patient_Diagnose> getDiagnosesByPatientId(String id);

}
