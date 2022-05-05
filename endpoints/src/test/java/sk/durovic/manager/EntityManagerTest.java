package sk.durovic.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import sk.durovic.configuration.EntityManagerConfiguration;
import sk.durovic.helper.Helper;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;
import sk.durovic.model.access.ContactEntity;
import sk.durovic.model.access.PatientEntity;

import javax.validation.ConstraintViolationException;
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

    private Patient patient;
    private Contact contact;

    @BeforeEach
    void setUp() throws Exception {
        entityManager = new EntityManager();
        setContext();

        PatientEntity pa = new PatientEntity();
        pa.setFirstName("majky");
        patient = EntityMapper.mapEntityToPersist(pa);

        ContactEntity ce = new ContactEntity();
        ce.setFullName("abc");

        contact = EntityMapper.mapEntityToPersist(ce);
    }

    @Test
    void saveTest() throws Exception {
        entityManager.save(patient);

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertEquals(patient.getId(), saved.getId());
    }

    @Test
    void saveContactWithoutParentEntity() throws Exception {
        assertThrows(ConstraintViolationException.class, () -> entityManager.save(contact));
    }

    @Test
    public void saveChildWithNonExistingParent() throws Exception {
        Helper.setIdOfInstance(patient, "testing");

        ContactEntity ce = EntityMapper.mapEntity(contact);
        ce.connectPatient(patient);

        contact = EntityMapper.mapEntityToPersist(ce);

        assertThrows(DataIntegrityViolationException.class,
                () -> entityManager.save(contact));
    }

    @Test
    void versioningEntityTest(){

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