package sk.durovic.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.durovic.model.Patient;
import sk.durovic.service.PatientService;

@Component
public class EntityManager {

    private final PatientService patientService;

    public EntityManager(PatientService patientService) {
        this.patientService = patientService;
    }

    public <T> T save(T object){
        return null;
    }

    public void saveTest(Patient o){
        Patient a = patientService.save(o);
    }
}
