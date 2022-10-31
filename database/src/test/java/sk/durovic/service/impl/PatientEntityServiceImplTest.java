package sk.durovic.service.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.helper.Helper;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.repository.PatientRepository;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class PatientEntityServiceImplTest {

    @Mock
    private PatientRepository repo;

    @InjectMocks
    private PatientEntityServiceImpl service;

    private PatientEntity patientE;
    private Patient patient;
    private String id;

    @BeforeEach
    void setUp() throws Exception {
        patientE = new PatientEntity();
        id = "tester";
        Helper.setIdOfInstance(patientE, id);
        patient = EntityMapper.mapEntityToPersist(patientE);
    }

    @Test
    void save() {
        Mockito.when(repo.save(patient)).thenReturn(patient);

        Patient saved = service.save(patient);

        Mockito.verify(repo, Mockito.atMostOnce()).save(patient);
        assertThat(saved, Matchers.notNullValue());
        assertThat(saved, Matchers.hasProperty("id", Matchers.is(id)));
    }

    @Test
    void delete() {
        Mockito.doNothing().when(repo).delete(patient);

        service.delete(patient);

        Mockito.verify(repo, Mockito.atMostOnce()).delete(patient);
    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(repo).deleteById(id);

        service.deleteById(id);

        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(id);
    }
}