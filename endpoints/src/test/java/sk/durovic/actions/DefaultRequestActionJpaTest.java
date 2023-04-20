package sk.durovic.actions;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import sk.durovic.model.BloodGroup;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientEntityService;
import sk.durovic.service.impl.PatientEntityServiceImpl;

import java.util.Collection;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DefaultRequestActionJpaTest {

    @Autowired
    private PatientRepository repo;
    private PatientEntityService service;
    private Patient patient;
    private RequestAction<Patient> requestAction;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        this.service = new PatientEntityServiceImpl(repo);
        this.patient = createTestPatient();
        this.requestAction = new DefaultRequestAction<>(service);
    }

    @Test
    void get() {
        Patient saved = service.save(patient);

        Collection<Patient> patients = requestAction.get(List.of(saved));

        MatcherAssert.assertThat(patients, Matchers.hasSize(1));

        Patient testPatient = patients.iterator().next();

        MatcherAssert.assertThat(testPatient.getFirstName(), Matchers.is(saved.getFirstName()));
        MatcherAssert.assertThat(saved.getId(), Matchers.is(testPatient.getId()));
    }

    @Test
    void post() {
    }

    @Test
    void put() {
    }

    @Test
    void delete() {
    }

    private Patient createTestPatient(){
        Patient patientTest = new Patient();
        patientTest.setFirstName("Marek");
        patientTest.setLastName("Durovic");
        patientTest.setBloodGroup(BloodGroup.AA);
        patientTest.setEmail("marek@mm.sk");

        return patientTest;
    }
}