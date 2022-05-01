package sk.durovic.manager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import sk.durovic.configuration.EntityManagerConfiguration;
import sk.durovic.helper.Helper;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Contact;
import sk.durovic.model.Disease;
import sk.durovic.model.Patient;
import sk.durovic.model.access.DiseaseEntity;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientService;
import sk.durovic.service.impl.PatientServiceImpl;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({EntityManagerConfiguration.class})
class EntityManagerTest {

    @Autowired
    private ApplicationContext context;

    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void saveTest() throws Exception {
        entityManager = new EntityManager();
        setContext();

        Contact ct = new Contact();

        Contact saved = entityManager.save(ct);
        saved.getId();

        Patient patient = entityManager.createEntity(Patient.class);
        Patient savedPatient = entityManager.save(patient);
        savedPatient.getId();

        PatientEntity pp = new PatientEntity();
        pp.setFirstName("adb");
        Patient novy = EntityMapper.mapEntity(pp);
        novy.getId();

        DiseaseEntity de = new DiseaseEntity();
        de.setName("proces");
        Helper.setIdOfInstance(de, 39L);
        Disease di = EntityMapper.mapEntity(de);
        di.getDescription();

        //patientRepository.save(patient);
        //Optional<Patient> saved = patientRepository.findById(patient.getId());

        //assertThat(saved.get(), Matchers.notNullValue());
    }

    private void setContext() throws Exception {
        Field field = entityManager.getClass().getDeclaredField("serviceContainer");
        field.setAccessible(true);
        ServiceContainer sc = (ServiceContainer) field.get(entityManager);
        Field ff = sc.getClass().getDeclaredField("context");
        ff.setAccessible(true);
        ff.set(sc, context);
    }
}