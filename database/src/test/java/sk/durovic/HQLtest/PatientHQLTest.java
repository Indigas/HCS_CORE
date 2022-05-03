package sk.durovic.HQLtest;

import org.hamcrest.Matchers;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.helper.Helper;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.model.Contact;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.impl.PatientServiceImpl;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(MockitoExtension.class)
public class PatientHQLTest {

    private SessionFactory sf;
    private Session session;
    private EntityManager entityManager;

    @Mock
    private PatientRepository repo;

    @InjectMocks
    private PatientServiceImpl service;

    @BeforeEach
    public void setUp(){
        sf= new Configuration().configure("test-hibernate.cfg.xml")
                .buildSessionFactory();
        session = sf.openSession();
        entityManager = session.getEntityManagerFactory().createEntityManager();
    }

    @AfterEach
    public void closeIt(){
        entityManager.close();
        session.close();
    }

    @Test
    public void createPatient() throws Exception {
        PatientEntity pe = new PatientEntity();
        pe.setFirstName("Marek");

        Patient pt = EntityMapper.mapEntityToPersist(pe);

        session.save(pt);
        session.beginTransaction().commit();
        Patient saved = entityManager.find(Patient.class, pt.getId());

        assertThat(pt, Matchers.equalTo(saved));

        session.delete(pt);
        session.beginTransaction().commit();
    }

    @Test
    public void createTwoPatientWithGeneratedId() throws Exception {
        PatientEntity pe = new PatientEntity();
        pe.setFirstName("Marek");
        pe.setLastName("kukucka");

        Patient patient = EntityMapper.mapEntityToPersist(pe);

        Transaction tr = session.beginTransaction();
        session.save(patient);

        PatientEntity secondPatient = new PatientEntity();
        secondPatient.setLastName("Uhrin");
        Patient second = EntityMapper.mapEntityToPersist(secondPatient);
        Helper.setIdOfInstance(second, patient.getId());

        save(second);
        tr.commit();

        assertTrue(checkEntityPersistence(second));

        tr.begin();
        session.delete(patient);
        session.delete(second);
        tr.commit();
    }

    private Object save(Object object){
        try {
            session.saveOrUpdate(object);
        } catch (NonUniqueObjectException e){
            System.out.println("NonUnique Exception");
            session.save(object);
        }
        return object;
    }

    private <T extends Serializable> boolean checkEntityPersistence(BaseEntityAbstractClass<T> o){
        return session.get(Patient.class, o.getId()) != null;
    }
}
