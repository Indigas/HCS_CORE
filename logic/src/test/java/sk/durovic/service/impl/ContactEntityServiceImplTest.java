package sk.durovic.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.Patient;
import sk.durovic.repository.ContactRepository;

@ExtendWith(MockitoExtension.class)
class ContactEntityServiceImplTest {

    @Mock
    private ContactRepository repo;

    @InjectMocks
    private ContactEntityServiceImpl service;

    private Patient patient;
//    private ContactEntity contactE;
//    private Contact contact;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        patient = new PatientEntity();
//        Helper.setIdOfInstance(patient, "test");
//        contactE = new ContactEntity();
//        contactE.connectPatient(patient);
//        contactE.setFullName("marek_contact");
//        Helper.setIdOfInstance(contactE, 22L);
//        contact = EntityMapper.mapEntityToPersist(contactE);
//    }
//
//    @Test
//    void save() {
//        Mockito.when(repo.save(contact)).thenReturn(contact);
//
//        Contact saved = service.save(contact);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).save(contact);
//        assertThat(saved, Matchers.notNullValue());
//        assertThat(saved, Matchers.is(contact));
//    }
//
//    @Test
//    void delete() {
//        Mockito.doNothing().when(repo).delete(contact);
//
//        service.delete(contact);
//
//        Mockito.verify(repo, Mockito.atMostOnce()).delete(contact);
//
//    }
//
//    @Test
//    void deleteById() {
//        Mockito.doNothing().when(repo).deleteById(contact.getId());
//
//        service.deleteById(contact.getId());
//
//        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(contact.getId());
//    }
}