package sk.durovic.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.Diagnose;
import sk.durovic.repository.DiagnoseRepository;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiagnoseServiceImplTest {

    @Mock
    private DiagnoseRepository repo;

    @InjectMocks
    private DiagnoseServiceImpl service;

    private Diagnose diagnose;

    @BeforeEach
    private void setUp(){
        diagnose = new Diagnose();
        diagnose.setTag("diag");
    }

    @Test
    void save() {
        Mockito.when(repo.save(diagnose)).thenReturn(diagnose);

        Diagnose saved = service.save(diagnose);

        Mockito.verify(repo, Mockito.atMostOnce()).save(diagnose);
        assertThat(saved, notNullValue());
        assertThat(saved, hasProperty("tag", is("diag")));
    }

    @Test
    void delete() {
        Mockito.doNothing().when(repo).delete(diagnose);

        service.delete(diagnose);

        Mockito.verify(repo, Mockito.atMostOnce()).delete(diagnose);

    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(repo).deleteById(1L);

        service.deleteById(1L);

        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(1L);
    }
}