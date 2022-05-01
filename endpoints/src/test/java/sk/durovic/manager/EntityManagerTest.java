package sk.durovic.manager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientService;
import sk.durovic.service.impl.PatientServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EntityManagerTest {

    private EntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private PatientService patientService = new PatientServiceImpl(patientRepository);

    @Test
    void saveTest() {
        patientService = new PatientServiceImpl(patientRepository);
        entityManager = new EntityManager(patientService);
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Testing");

        entityManager.saveTest(patient);
        //patientRepository.save(patient);
        Optional<Patient> saved = patientRepository.findById(patient.getId());

        assertThat(saved.get(), Matchers.notNullValue());
    }
}