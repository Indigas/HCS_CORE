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
import sk.durovic.model.BaseEntity;
import sk.durovic.model.Contact;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.model.Patient;
import sk.durovic.repository.ContactRepository;

import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepository repo;

    @InjectMocks
    private ContactServiceImpl service;

    private Patient patient;
    private Contact contact;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        patient = new PatientEntity();
        Helper.setIdOfInstance(patient, "test");
        contact = new Contact();
        contact.setPatient(patient);
        contact.setFullName("marek_contact");
        Helper.setIdOfInstance(contact, 22L);
    }

    @Test
    void save() {
        Mockito.when(repo.save(contact)).thenReturn(contact);

        Contact saved = service.save(contact);

        Mockito.verify(repo, Mockito.atMostOnce()).save(contact);
        assertThat(saved, Matchers.notNullValue());
        assertThat(saved, Matchers.is(contact));
    }

    @Test
    void delete() {
        Mockito.doNothing().when(repo).delete(contact);

        service.delete(contact);

        Mockito.verify(repo, Mockito.atMostOnce()).delete(contact);

    }

    @Test
    void deleteById() {
        Mockito.doNothing().when(repo).deleteById(contact.getId());

        service.deleteById(contact.getId());

        Mockito.verify(repo, Mockito.atMostOnce()).deleteById(contact.getId());
    }
}