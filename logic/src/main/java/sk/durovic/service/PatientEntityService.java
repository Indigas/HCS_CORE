package sk.durovic.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueObjectException;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;

@Slf4j
public abstract class PatientEntityService extends EntityService<Patient, String, PatientRepository> {

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
