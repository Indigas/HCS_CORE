package sk.durovic.service.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.Disease;
import sk.durovic.repository.DiseaseRepository;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiseaseServiceImplTest {

    @Mock
    private DiseaseRepository repo;

    @InjectMocks
    private DiseaseServiceImpl service;

    private Disease disease;

    @BeforeEach
    void setUp() {
        disease = new Disease();
        disease.setName("heart-attack");
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