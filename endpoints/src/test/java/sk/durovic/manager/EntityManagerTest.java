package sk.durovic.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import sk.durovic.configuration.EntityManagerConfiguration;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({EntityManagerConfiguration.class})
class EntityManagerTest {

    @Autowired
    private ApplicationContext context;

    private EntityManager entityManager;

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void saveTest() throws Exception {
        entityManager = new EntityManager();
        setContext();
        PatientEntity pa = new PatientEntity();
        pa.setFirstName("majky");
        Patient pt = EntityMapper.mapEntityToPersist(pa);
        entityManager.save(pt);

        Patient saved = testEntityManager.find(Patient.class, pt.getId());

        assertEquals(pt.getId(), saved.getId());
    }

    @Test
    void versioningEntityTest(){

    }

    private void setContext() throws Exception {
        Field field = entityManager.getClass().getDeclaredField("serviceContainer");
        field.setAccessible(true);
        ServiceContainer sc = (ServiceContainer) field.get(entityManager);
        Field ff = sc.getClass().getDeclaredField("context");
        ff.setAccessible(true);
        ff.set(sc, context);
    }
}