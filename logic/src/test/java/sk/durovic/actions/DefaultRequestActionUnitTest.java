package sk.durovic.actions;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.BloodGroup;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;
import sk.durovic.service.ContactEntityService;
import sk.durovic.service.PatientEntityService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DefaultRequestActionUnitTest {

    private RequestAction<Patient> patientRequestAction;
    private RequestAction<Contact> contactRequestAction;

    @Mock
    private PatientEntityService patientEntityService;

    @Mock
    private ContactEntityService contactEntityService;

    @Captor
    private ArgumentCaptor<Collection<Patient>> argumentCaptor;

    private Patient patient;
    private Contact contact;

    @BeforeEach
    public void setUp(){
        patientRequestAction = new DefaultRequestAction<>(patientEntityService);
        contactRequestAction = new DefaultRequestAction<>(contactEntityService);
        patient = createTestPatient();
        contact = createTestContact();
    }

    @Test
    public void getByEntity(){
        Mockito.when(patientEntityService.loadAll(Mockito.anyCollection())).thenReturn(List.of((patient)));

        Collection<Patient> loaded = patientRequestAction.get(List.of(patient));

        MatcherAssert.assertThat(loaded, Matchers.hasSize(1));
        assertEquals(loaded.iterator().next(), patient);
    }

    @Test
    public void getWithEmptyCollection(){
        Mockito.verify(patientEntityService, Mockito.never()).loadAll(Mockito.anyCollection());

        Collection<Patient> loaded = patientRequestAction.get(List.of());

        MatcherAssert.assertThat(loaded, Matchers.empty());
    }

    @Test
    public void getByExample(){
        Patient test = new Patient();
        test.setFirstName("Igor");

        Mockito.verify(patientEntityService, Mockito.never()).findAllByExample(Mockito.any());

        Collection<Patient> loaded = patientRequestAction.get(List.of(test));

        MatcherAssert.assertThat(loaded, Matchers.hasSize(0));
    }

    @Test
    public void updateEntityMergerTest(){
        Patient newPatient = new Patient();
        newPatient.setId(patient.getId());
        newPatient.setFirstName("Igor");

        Mockito.when(patientEntityService.loadAll(Mockito.anyCollection())).thenReturn(List.of(patient));

        patientRequestAction.put(List.of(newPatient));

        Mockito.verify(patientEntityService, Mockito.atMostOnce()).saveAll(argumentCaptor.capture());

        Collection<Patient> catched = argumentCaptor.getValue();

        MatcherAssert.assertThat(catched, Matchers.hasSize(1));

        Patient mergedPatient = catched.iterator().next();
        assertEquals(mergedPatient, patient);
        MatcherAssert.assertThat(mergedPatient, Matchers.hasProperty("firstName", Matchers.is(newPatient.getFirstName())));

    }

    @Test
    public void getWithExampleInvocation(){
        Contact contactExample = new Contact();
        contactExample.setPatient(patient);

        Mockito.when(contactEntityService.findAllByExample(Mockito.any())).thenReturn(List.of(contact));

        Collection<Contact> contacts = contactRequestAction.get(List.of(contactExample));

        MatcherAssert.assertThat(contacts, Matchers.hasSize(1));
        MatcherAssert.assertThat(contacts.iterator().next(), Matchers.is(contact));
    }


    private Patient createTestPatient(){
        Patient patientTest = new Patient();
        patientTest.setId("123");
        patientTest.setFirstName("Marek");
        patientTest.setLastName("Durovic");
        patientTest.setBloodGroup(BloodGroup.AA);
        patientTest.setEmail("marek@mm.sk");

        return patientTest;
    }

    private Contact createTestContact(){
        Contact contactTest = new Contact();
        contactTest.setId(555L);
        contactTest.setFullName("Jan Kopka");
        contactTest.setPatient(patient);
        contactTest.setTelephone("+421999555888");

        return contactTest;
    }

}