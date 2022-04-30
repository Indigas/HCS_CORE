package sk.durovic.HQLtest;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.model.Contact;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.impl.PatientServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PatientHQLTest {

    private SessionFactory sf;
    private Session session;
    private PatientEntity patient;

    @Mock
    private PatientRepository repo;

    @InjectMocks
    private PatientServiceImpl service;

    @BeforeEach
    public void setUp(){
        sf= new Configuration().configure("test-hibernate.cfg.xml")
                .addAnnotatedClass(Patient.class)
                .addAnnotatedClass(Contact.class).buildSessionFactory();
        session = sf.openSession();
        patient = new PatientEntity();

    }

    @AfterEach
    public void closeIt(){
        //session.delete(patient);
        //session.beginTransaction().commit();

        session.close();
    }

    @Test
    public void createPatient(){
        Contact ct = new Contact();
        ct.setPatient(patient);
        patient.connectContact(ct);
        patient.setFirstName("marek");
        patient.setLastName("durovic");
        Patient pt = patient;

        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        //em.persist(patient);
        //em.persist(ct);
        session.save(pt);
        em.getTransaction().commit();
        em.close();

        session.beginTransaction().commit();

        assertTrue(checkEntityPersistence(patient));

    }

    @Test
    @Disabled
    public void createTwoPatientWithGeneratedId(){
        Mockito.when(repo.save(patient)).thenReturn((PatientEntity) save(patient));

        PatientEntity secondPatient = new PatientEntity();
        secondPatient.setLastName("Uhrin");
        patient.setLastName("kukucka");

        Mockito.when(repo.save(secondPatient)).thenReturn((PatientEntity) save(secondPatient));

        service.save(patient);
        service.save(secondPatient);

        session.beginTransaction().commit();

        assertTrue(checkEntityPersistence(secondPatient));

        session.delete(secondPatient);
    }

    private Object save(Object object){
        try {
            session.save(object);
        } catch (NonUniqueObjectException e){
            System.out.println("NonUnique Exception");
            save(object);
        }
        return object;
    }

    private <T extends Serializable> boolean checkEntityPersistence(BaseEntityAbstractClass<T> o){
        return session.get(Patient.class, o.getId()) != null;
    }
}
