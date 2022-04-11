package sk.durovic.HQLtest;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.durovic.model.BaseEntityInterface;
import sk.durovic.model.Patient;
import sk.durovic.model.Patient_Diagnose;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientHQLTest {

    private SessionFactory sf;
    private Session session;
    private Patient patient;

    @BeforeEach
    public void setUp(){
        sf= new Configuration().configure("test-hibernate.cfg.xml").buildSessionFactory();
        session = sf.openSession();
        patient = new Patient();
    }

    @AfterEach
    public void closeIt(){
        session.delete(patient);
        session.beginTransaction().commit();

        session.close();
    }

    @Test
    public void createPatient(){
        patient.setFirstName("marek");
        patient.setLastName("durovic");

        session.save(patient);

        session.beginTransaction().commit();

        assertTrue(checkEntityPersistence(patient));

    }

    @Test
    public void createTwoPatientWithGeneratedId(){
        Patient secondPatient = new Patient();
        secondPatient.setLastName("Uhrin");
        patient.setLastName("kukucka");

        session.save(patient);
        session.save(secondPatient);

        session.beginTransaction().commit();

        assertTrue(checkEntityPersistence(secondPatient));

        session.delete(secondPatient);
    }

    private <T extends Serializable> boolean checkEntityPersistence(BaseEntityInterface<T> o){
        return session.get(Patient.class, o.getId()) != null;
    }
}
