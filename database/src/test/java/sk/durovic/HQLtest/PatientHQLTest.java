package sk.durovic.HQLtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.durovic.model.Patient;

public class PatientHQLTest {

    private SessionFactory sf;
    private Session session;

    @BeforeEach
    public void setUp(){
        sf= new Configuration().configure().buildSessionFactory();
        session = sf.openSession();
    }

    @Test
    public void createPatient(){
        //session.beginTransaction();

        Patient patient = new Patient();
        patient.setId("SLIBIV25");
        patient.setFirstName("marek");
        patient.setLastName("durovic");

        session.save(patient);

        session.beginTransaction().commit();
        session.close();
    }
}
