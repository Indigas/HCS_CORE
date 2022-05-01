package sk.durovic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import sk.durovic.model.MedicalRecord;
import sk.durovic.repository.*;
import sk.durovic.service.ContactService;
import sk.durovic.service.PatientService;
import sk.durovic.service.impl.ContactServiceImpl;
import sk.durovic.service.impl.PatientServiceImpl;

@TestConfiguration
public class EntityManagerConfiguration {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @Bean
    public ContactService getContactService(){
        return new ContactServiceImpl(contactRepository);
    }

    @Bean
    public PatientService getPatientService(){
        return new PatientServiceImpl(patientRepository);
    }

}
