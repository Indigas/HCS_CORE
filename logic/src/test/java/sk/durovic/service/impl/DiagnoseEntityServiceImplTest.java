package sk.durovic.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.Diagnose;
import sk.durovic.repository.DiagnoseRepository;

@ExtendWith(MockitoExtension.class)
class DiagnoseEntityServiceImplTest {

    @Mock
    private DiagnoseRepository repo;

    @InjectMocks
    private DiagnoseEntityServiceImpl service;

//    private DiagnoseEntity diagnoseE;
    private Diagnose diagnose;

    @BeforeEach
    private void setUp() throws Exception {
//        diagnoseE = new DiagnoseEntity();
//        diagnoseE.setTag("diag");
//        diagnose = EntityMapper.mapEntityToPersist(diagnoseE);
    }

//    @Test
//    void save() {
//        Mockito.when(repo.save(diagnose)).thenReturn(diagnose);
//
//        Diagnose saved = service.save(diagnose);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).save(diagnose);
//        assertThat(saved, notNullValue());
//        assertThat(saved, hasProperty("tag", is("diag")));
//    }
//
//    @Test
//    void delete() {
//        Mockito.doNothing().when(repo).delete(diagnose);
//
//        service.delete(diagnose);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).delete(diagnose);
//
//    }
//
//    @Test
//    void deleteById() {
//        Mockito.doNothing().when(repo).deleteById(1L);
//
//        service.deleteById(1L);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(1L);
//    }
}