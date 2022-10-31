package sk.durovic.mapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import static org.hamcrest.MatcherAssert.assertThat;

class EntityMapperTest {

    @Test
    public void mapEntityToPersistTest() throws Exception {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Marek");

        Patient patient = EntityMapper.mapEntityToPersist(patientEntity);
        assertThat(patient, Matchers.hasProperty("firstName", Matchers.is(patientEntity.getFirstName())));
    }

    @Test
    public void mapEntityTest() throws Exception {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("Marek");

        Patient patient = EntityMapper.mapEntityToPersist(patientEntity);
        PatientEntity converted = EntityMapper.mapEntity(patient);

        assertThat(converted, Matchers.hasProperty("firstName", Matchers.is(patient.getFirstName())));
    }

}