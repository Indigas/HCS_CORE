package sk.durovic.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.repository.*;
import sk.durovic.service.*;
import sk.durovic.service.impl.*;

@TestConfiguration
public class EntityManagerConfiguration {

    @Autowired
    private ApplicationContext context;

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
    public ContactEntityService getContactService(){
        return new ContactEntityServiceImpl(contactRepository);
    }

    @Bean
    public PatientEntityService getPatientService(){
        return new PatientEntityServiceImpl(patientRepository);
    }

    @Bean
    public MedicalRecordEntityService getMedicalRecordService(){
        return new MedicalRecordEntityServiceImpl(medicalRecordRepository);
    }

    @Bean
    public DiseaseEntityService getDiseaseService(){
        return new DiseaseEntityServiceImpl(diseaseRepository);
    }

    @Bean
    public DiagnoseEntityService getDiagnoseService(){
        return new DiagnoseEntityServiceImpl(diagnoseRepository);
    }

    @Bean
    public EntityManagerCreator getFactory() {
        return new EntityManagerCreator(context);
    }

}
