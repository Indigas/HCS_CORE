package sk.durovic.manager.basic;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import sk.durovic.configuration.EntityManagerConfiguration;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.manager.Container;
import sk.durovic.manager.EntityContainer;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;
import sk.durovic.model.access.ContactEntity;
import sk.durovic.model.access.PatientEntity;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({EntityManagerConfiguration.class})
class EntityManagerBasicTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TestEntityManager testEntityManager;

    private EntityManager manager;
    private EntityContainer entityContainer;
    private Patient patient;
    private Contact contact;


    @BeforeEach
    void setUp() throws Exception {
        this.manager = EntityManagerFactory.getBasicEntityManager();
        setContext();
        setVariables();
        this.entityContainer = getEntityContainer(this.manager);
    }


    @Test
    void save() {
        manager.save(patient);

        Optional<Patient> saved = entityContainer.onLoad(patient);

        assertTrue(saved.isPresent());
        assertThat(saved.get(), Matchers.hasProperty("id", Matchers.is("PatientTest")));
    }

    @Test
    void saveWithChange() throws Exception{
        manager.save(patient);

        PatientEntity entity = EntityMapper.mapEntity(patient);
    }

    @Test
    void load() {
        manager.save(patient);

        Optional<Patient> saved = manager.load(Patient.class, patient.getId());

        assertTrue(saved.isPresent());
        assertThat(saved.get(), Matchers.hasProperty("id", Matchers.is("PatientTest")));

    }

    @Test
    void contains() {
        manager.save(patient);

        boolean saved = manager.contains(patient);

        assertTrue(saved);
    }

    @Test
    void lock() {
        manager.lock(patient);

        Version version = patient.getVersion();

        assertTrue(version.isLocked());
    }

    @Test
    void shouldNotChangeOnLockStatus() throws Exception{
        manager.lock(patient);

        PatientEntity toChange = EntityMapper.mapEntity(patient);
        toChange.setLastName("Spring");

        assertThrows(EntityChangeVersion.class, () -> manager.lock(EntityMapper.mapEntityToPersist(toChange)));
        assertThrows(EntityChangeVersion.class, () -> manager.save(EntityMapper.mapEntityToPersist(toChange)));

    }

    @Test
    void release() {
        manager.lock(patient);

        manager.release(patient);
        assertTrue(patient.getVersion().isOptimisticLock());
    }

    @Test
    void refresh() {
    }

    @Test
    void remove() {
    }

    @Test
    void flush() {
    }

    @Test
    void commit() {
    }

    @Test
    void close() {
    }

    @Test
    void clear() {
    }

    @Test
    void getReference() {
    }

    private void setVariables() throws Exception {
        PatientEntity pa = new PatientEntity();
        pa.setFirstName("majky");
        this.patient = EntityMapper.mapEntityToPersist(pa);
        EntityManipulator.setIdOfReferenceEntity(patient, "PatientTest");

        ContactEntity ce = new ContactEntity();
        ce.setFullName("abc");
        this.contact = EntityMapper.mapEntityToPersist(ce);
        EntityManipulator.setIdOfReferenceEntity(contact, 10L);
    }

    private void setContext() throws Exception {
        Field field = manager.getClass().getDeclaredField("serviceContainer");
        field.setAccessible(true);
        ServiceContainerBasic sc = (ServiceContainerBasic) field.get(manager);
        Field ff = sc.getClass().getDeclaredField("context");
        ff.setAccessible(true);
        ff.set(sc, context);
    }

    private EntityContainer getEntityContainer(EntityManager manager) throws Exception {
        Field field = manager.getClass().getDeclaredField("entityContainer");
        field.setAccessible(true);
        return (EntityContainer) field.get(manager);
    }

    private Container getContainer(EntityContainer container) throws Exception{
        Field field = container.getClass().getDeclaredField("container");
        field.setAccessible(true);
        return (Container) field.get(container);
    }
}