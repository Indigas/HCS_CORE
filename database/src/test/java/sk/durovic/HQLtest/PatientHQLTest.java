package sk.durovic.HQLtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

    @BeforeEach
    public void setUp(){
        sf= new Configuration().configure("test-hibernate.cfg.xml").buildSessionFactory();
        session = sf.openSession();
    }

    @Test
    public void createPatient(){

        Patient patient = new Patient();
        patient.setFirstName("marek");
        patient.setLastName("durovic");

        session.save(patient);

        session.beginTransaction().commit();

        assertTrue(checkEntityPersistence(patient));

        session.delete(patient);
        session.beginTransaction().commit();

        session.close();
    }

    private <T extends Serializable> boolean checkEntityPersistence(BaseEntityInterface<T> o){
        return session.get(Patient.class, o.getId()) != null;
    }
}
