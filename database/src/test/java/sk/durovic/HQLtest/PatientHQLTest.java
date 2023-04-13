package sk.durovic.HQLtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;

import javax.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class PatientHQLTest {

    private SessionFactory sf;
    private Session session;
    private EntityManager entityManager;

    private Patient patient;
    private Contact contact;

//    @BeforeEach
//    public void setUp() throws Exception {
//        sf= new Configuration().configure("test-hibernate.cfg.xml")
//                .buildSessionFactory();
//        session = sf.openSession();
//        entityManager = session.getEntityManagerFactory().createEntityManager();
//
//        PatientEntity pe = new PatientEntity();
//        pe.setFirstName("Marek");
//
//        patient = EntityMapper.mapEntityToPersist(pe);
//
//        ContactEntity ce = new ContactEntity();
//        ce.setFullName("abc");
//        contact = EntityMapper.mapEntityToPersist(ce);
//    }
//
//    @AfterEach
//    public void closeIt(){
//        entityManager.close();
//        session.close();
//    }
//
//    @Test
//    public void createPatient() {
//
//        session.save(patient);
//        session.beginTransaction().commit();
//        Patient saved = entityManager.find(Patient.class, patient.getId());
//
//        assertThat(patient, Matchers.equalTo(saved));
//
//        session.delete(patient);
//        session.beginTransaction().commit();
//    }
//
//    @Test
//    public void saveChildWithoutParent() throws Exception {
//        assertThrows(ConstraintViolationException.class, () -> session.save(contact));
//    }
//
//    @Test
//    public void createTwoPatientWithGeneratedId() throws Exception {
//        PatientEntity secondPatient = new PatientEntity();
//        secondPatient.setLastName("Uhrin");
//        Patient second = EntityMapper.mapEntityToPersist(secondPatient);
//
//        Helper.setIdOfInstance(second, patient.getId());
//
//        Transaction tr = session.beginTransaction();
//        session.save(patient);
//
//        save(second);
//        tr.commit();
//
//        assertTrue(checkEntityPersistence(second));
//
//        tr.begin();
//        session.delete(patient);
//        session.delete(second);
//        tr.commit();
//    }
//
//    @Test
//    public void saveChildWithNonExistingParent() throws Exception {
//        Helper.setIdOfInstance(patient, "testing");
//        ContactEntity ce = EntityMapper.mapEntity(contact);
//        ce.connectPatient(patient);
//
//        contact = EntityMapper.mapEntityToPersist(ce);
//
//        assertThrows(org.hibernate.exception.ConstraintViolationException.class, () -> session.save(contact));
//    }
//
//    private Object save(Object object){
//        try {
//            session.saveOrUpdate(object);
//        } catch (NonUniqueObjectException e){
//            System.out.println("NonUnique Exception");
//            session.save(object);
//        }
//        return object;
//    }
//
//    private <T extends Serializable> boolean checkEntityPersistence(BaseEntityAbstractClass<T> o){
//        return session.get(Patient.class, o.getId()) != null;
//    }
}
