package sk.durovic.manager;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

class ContainerTest {

    private Container container;
    private Patient patient;

    @BeforeEach
    void setUp() throws Exception {
        container = Container.class.getDeclaredConstructor().newInstance();
        patient = EntityMapper.mapEntityToPersist(new PatientEntity());
        EntityManipulator.setIdOfReferenceEntity(patient, "test123");
    }

    @Test
    void saveAndLoad() throws Exception {
        container.save(patient);
        PatientEntity ent = new PatientEntity();
        EntityManipulator.setIdOfReferenceEntity(ent, "test123");

        Optional<Patient> saved = container.load(EntityMapper.mapEntityToPersist(ent));

        assertTrue(saved.isPresent());
        assertThat(saved.get(), Matchers.hasProperty("id", Matchers.is("test123")));
    }

    @Test
    void addToContainer() {
        container.addToContainer(patient);
        List<?> list = container.getListOfEntities(Version.Status.OPTIMISTIC_LOCK, patient.getClass());

        assertThat(list, Matchers.notNullValue());
        assertThat(list, Matchers.hasSize(1));
    }

    @Test
    void removeFromContainer() {
        container.save(patient);
        List<?> list = container.getByClass(patient.getClass());

        assertThat(list, Matchers.hasSize(1));
        assertTrue(container.removeFromContainer(patient));

        list = container.getByClass(patient.getClass());
        assertThat(list, Matchers.hasSize(0));
    }

    @Test
    void onChangeStatus() {
        container.save(patient);
        List<?> toSaveList = container.getByStatus(Version.Status.TO_SAVE);

        assertThat(toSaveList, Matchers.hasSize(1));

        patient.getVersion().toRemove();
        container.onChangeStatus(patient);

        toSaveList = container.getByStatus(Version.Status.TO_SAVE);
        List<?> toRemoveList = container.getByStatus(Version.Status.TO_REMOVE);

        assertThat(toSaveList, Matchers.hasSize(0));
        assertThat(toRemoveList, Matchers.hasSize(1));
    }

    @Test
    void getListOfEntities() {
        List<?> list = container.getListOfEntities(Version.Status.LOCK, Patient.class);

        assertThat(list, Matchers.notNullValue());
    }

    @Test
    void getByStatus() {
        container.save(patient);

        List<?> list = container.getByStatus(patient.getVersion().getStatus());

        assertThat(list, Matchers.notNullValue());
        assertThat(list, Matchers.hasSize(1));
    }

    @Test
    void getByClass() throws Exception {
        container.save(patient);
        List<?> list = container.getByClass(patient.getClass());

        assertThat(list, Matchers.notNullValue());
        assertThat(list, Matchers.hasSize(1));
    }

    @Test
    void clear() {
        container.save(patient);
        List<?> list = container.getByClass(patient.getClass());
        assertThat(list, Matchers.hasSize(1));

        container.clear();
        list = container.getByClass(patient.getClass());

        assertThat(list, Matchers.hasSize(0));
    }
}