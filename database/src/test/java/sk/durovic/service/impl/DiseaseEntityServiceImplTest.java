package sk.durovic.service.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Disease;
import sk.durovic.model.access.DiseaseEntity;
import sk.durovic.repository.DiseaseRepository;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class DiseaseEntityServiceImplTest {

    @Mock
    private DiseaseRepository repo;

    @InjectMocks
    private DiseaseEntityServiceImpl service;

    private DiseaseEntity diseaseE;
    private Disease disease;

    @BeforeEach
    void setUp() throws Exception {
        diseaseE = new DiseaseEntity();
        diseaseE.setName("heart-attack");
        disease = EntityMapper.mapEntityToPersist(diseaseE);
    }

    @Test
    void save() {
        Mockito.when(repo.save(disease)).thenReturn(disease);

        Disease saved = service.save(disease);

        Mockito.verify(repo, Mockito.atMostOnce()).save(disease);
        assertThat(saved, Matchers.notNullValue());
        assertThat(saved, Matchers.hasProperty("name", Matchers.is("heart-attack")));
    }

    @Test
    void delete() {
        Mockito.doNothing().when(repo).delete(disease);

        service.delete(disease);

        Mockito.verify(repo, Mockito.atMostOnce()).delete(disease);

    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(repo).deleteById(1L);

        service.deleteById(1L);

        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(1L);
    }
}