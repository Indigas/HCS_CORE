package sk.durovic.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;

import java.util.Optional;

@Slf4j
public abstract class PatientService extends Service<Patient, String, PatientRepository> {

    @Override
    public Patient save(Patient object) {

            try {
                return repo.save(object);
            } catch (NonUniqueObjectException e) {
                log.debug("NonUniqueException");
                return save(object);
            }

    }
}
