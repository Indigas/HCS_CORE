package sk.durovic.service;

import org.springframework.data.repository.CrudRepository;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;

public abstract class PatientService extends Service<Patient, String, PatientRepository> {


}
