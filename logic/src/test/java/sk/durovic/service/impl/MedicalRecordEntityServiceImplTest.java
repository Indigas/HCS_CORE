package sk.durovic.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.repository.MedicalRecordRepository;

@ExtendWith(MockitoExtension.class)
class MedicalRecordEntityServiceImplTest {

    @Mock
    private MedicalRecordRepository repo;

    @InjectMocks
    private MedicalRecordEntityServiceImpl service;

//    private MedicalRecordEntity medicalRecordE;
//    private MedicalRecord medicalRecord;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        medicalRecordE = new MedicalRecordEntity();
//        medicalRecordE.setText("test");
//        medicalRecord = EntityMapper.mapEntityToPersist(medicalRecordE);
//    }
//
//    @Test
//    void save() {
//        Mockito.when(repo.save(medicalRecord)).thenReturn(medicalRecord);
//
//        MedicalRecord saved = service.save(medicalRecord);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).save(medicalRecord);
//        assertThat(saved, Matchers.notNullValue());
//        assertThat(saved, Matchers.hasProperty("text", Matchers.is("test")));
//    }
//
//    @Test
//    void delete() {
//        Mockito.doNothing().when(repo).delete(medicalRecord);
//
//        service.delete(medicalRecord);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).delete(medicalRecord);
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