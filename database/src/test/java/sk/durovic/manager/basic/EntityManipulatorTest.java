package sk.durovic.manager.basic;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import static org.hamcrest.MatcherAssert.assertThat;

class EntityManipulatorTest {

    @Test
    void getEntityClass() {
        assertThat(EntityManipulator.getEntityClass(new PatientEntity()), Matchers.is(Patient.class));

    }

    @Test
    void setIdOfReferenceEntity() {
        PatientEntity patientEntity = new PatientEntity();
        EntityManipulator.setIdOfReferenceEntity(patientEntity, "test123");

        assertThat(patientEntity.getId(), Matchers.is("test123"));
    }
}