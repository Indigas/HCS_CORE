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
import sk.durovic.exception.EntityIntegrationException;
import sk.durovic.manager.Container;
import sk.durovic.manager.EntityContainer;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;
import sk.durovic.model.access.ContactEntity;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.service.PatientEntityService;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({EntityManagerConfiguration.class})
class EntityManagerBasicTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private EntityManagerCreator factory;

    @Autowired
    private TestEntityManager testEntityManager;

    private EntityManager manager;
    private EntityContainer entityContainer;
    private Patient patient;
    private Contact contact;

    @Autowired
    private PatientEntityService service;


    @BeforeEach
    void setUp() throws Exception {
        this.manager = factory.getBasicEntityManager();
        //setContext();
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

        PatientEntity entity = new PatientEntity(patient);
        Patient entityPatient = entity.createPatient();
        manager.save(entityPatient);

        assertTrue(manager.contains(entityPatient));
        assertThat(getContainer(entityContainer).getByClass(Patient.class), Matchers.hasSize(1));
    }

    @Test
    void saveContactWithoutParentEntity() {
        final Throwable[] throwableThread = new Throwable[1];
        assertThrows(EntityIntegrationException.class, () -> {
            manager.save(contact);
            manager.flush();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread thread, Throwable throwable) {
                        throwableThread[0] =throwable;
                    }
            });
            Thread.currentThread().join(5000);
            if(throwableThread[0].getClass() == EntityIntegrationException.class)
                throw new EntityIntegrationException("");

        });
    }

    @Test
    public void saveChildWithNonExistingParent() throws Exception {
        ContactEntity ce = new ContactEntity(contact);
        ce.connectPatient(patient);
        contact = ce.createContact();

        final Throwable[] throwableThread = new Throwable[1];
        assertThrows(EntityIntegrationException.class, () -> {
            manager.save(contact);
            manager.flush();
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable throwable) {
                    throwableThread[0] =throwable;
                }
            });
            Thread.currentThread().join(5000);
            if(throwableThread[0].getClass() == EntityIntegrationException.class)
                throw new EntityIntegrationException("");

        });
    }

    @Test
    void load() {
        manager.save(patient);

        Optional<Patient> saved = manager.load(Patient.class, patient.getId());

        assertTrue(saved.isPresent());
        assertThat(saved.get(), Matchers.hasProperty("id", Matchers.is("PatientTest")));

    }

    @Test
    void loadEntityNotInContainer(){
        Optional<Patient> loaded = manager.load(Patient.class, patient.getId());

        assertTrue(loaded.isEmpty());
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
    void release() throws Exception {
        manager.lock(patient);

        manager.release(patient);
        assertTrue(getContainer(entityContainer).getByStatus(Version.Status.OPTIMISTIC_LOCK).contains(patient));
        assertTrue(patient.getVersion().isOptimisticLock());
    }

    @Test
    void refresh() {
    }

    @Test
    void remove() {
        manager.save(patient);
        manager.remove(patient);

        assertTrue(patient.getVersion().isToRemove());
    }

    @Test
    void removePersisted() throws InterruptedException {
        PatientEntity entity = new PatientEntity();
        entity.setFirstName("Persisted");
        patient = entity.createPatient();
        manager.save(patient);
        manager.flush();
        Thread.currentThread().join(5000);

        manager.remove(patient);
        manager.flush();
        Thread.currentThread().join(5000);

        Patient saved = testEntityManager.find(Patient.class, patient.getId());

        assertNull(saved);
    }

    @Test
    void flush() throws InterruptedException {
        Patient persisted = executeFlushOrCommit(EntityManager::flush);

        assertThat(persisted, Matchers.is(patient));
        assertThat(persisted, Matchers.hasProperty("lastName", Matchers.is("entity")));
        assertTrue(manager.contains(patient));
    }

    @Test
    void commit() throws InterruptedException {
        Patient persisted = executeFlushOrCommit(EntityManager::commit);

        assertThat(persisted, Matchers.is(patient));
        assertThat(persisted, Matchers.hasProperty("lastName", Matchers.is("entity")));
        assertFalse(manager.contains(patient));
    }

    @Test
    void removeFromContainer(){
        manager.save(patient);
        manager.removeFromContainer(patient);

        assertFalse(manager.contains(patient));
    }


    @Test
    void getReference() {
        Patient ref = manager.getReference(Patient.class, "PatientTest");

        assertThat(ref, Matchers.hasProperty("id", Matchers.is("PatientTest")));
    }

    private Patient executeFlushOrCommit(Consumer<EntityManager> consumer) throws InterruptedException {
        PatientEntity entity = new PatientEntity();
        entity.setLastName("entity");
        patient = entity.createPatient();

        manager.save(patient);
        consumer.accept(manager);

        Thread.currentThread().join(5000);
        Patient persisted = testEntityManager.find(Patient.class, patient.getId());
        manager.remove(patient);
        consumer.accept(manager);

        Thread.currentThread().join(5000);
        return persisted;
    }

    private void setVariables() throws Exception {
        PatientEntity pa = new PatientEntity();
        pa.setFirstName("majky");
        this.patient = pa.createPatient();
        EntityManipulator.setIdOfReferenceEntity(patient, "PatientTest");

        ContactEntity ce = new ContactEntity();
        ce.setFullName("abc");
        this.contact = ce.createContact();
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