package sk.durovic.service.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientEntityService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PatientEntityServiceImplJpaTest {

    @Autowired
    private PatientRepository repo;
    private PatientEntityService service;
    private Patient patient;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        this.service = new PatientEntityServiceImpl(repo);
        PatientEntity entity = new PatientEntity();
        entity.setLastName("test");
        this.patient = entity.createPatient();
    }

    @Test
    void save() {
        service.save(patient);

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertThat(saved, Matchers.is(patient));
    }

    @Test
    void saveWithExistingId(){
        service.save(patient);

        PatientEntity entity = new PatientEntity(patient);
        entity.setFirstName("Spring");
        patient = entity.createPatient();
        service.save(patient);

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertThat(saved, Matchers.is(patient));
        assertThat(saved, Matchers.hasProperty("firstName", Matchers.is("Spring")));
    }

    @Test
    void delete() {
        service.save(patient);
        service.delete(patient);

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertThat(saved, Matchers.nullValue());
        assertThat(patient, Matchers.hasProperty("id", Matchers.notNullValue()));
    }

    @Test
    void deleteById() {
        service.save(patient);
        service.deleteById(patient.getId());

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertThat(saved, Matchers.nullValue());
        assertThat(patient, Matchers.hasProperty("id", Matchers.notNullValue()));
    }

    @Test
    void load() {
        service.save(patient);
        Optional<Patient> saved = service.load(patient.getId());

        assertTrue(saved.isPresent());
        assertThat(saved.get(), Matchers.is(patient));
    }
}