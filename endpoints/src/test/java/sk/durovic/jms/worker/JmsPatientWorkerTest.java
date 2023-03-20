package sk.durovic.jms.worker;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import sk.durovic.dto.PatientDto;
import sk.durovic.jms.events.entity.PatientEvent;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;
import sk.durovic.jms.messaging.worker.service.JmsPatientWorker;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.BloodGroup;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientEntityService;
import sk.durovic.service.impl.PatientEntityServiceImpl;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class JmsPatientWorkerTest {


    @Mock
    private EntityServiceManager manager;

    @Mock
    private ServiceContainer container;

    private PatientEntityService service;

    private JmsPatientWorker worker;
    @Autowired
    private PatientRepository repository;

    private Event<PatientDto> event;

    @BeforeEach
    public void setUp(){
        this.service = new PatientEntityServiceImpl(repository);

        Mockito.when(manager.getServiceContainer()).thenReturn(container);
        Mockito.when(container.getService(Patient.class)).thenReturn(Optional.of(service));

        this.worker = new JmsPatientWorker(manager);


    }



    @Test
    @Disabled
    @Transactional
    void processMessageWithReply() {
        this.event = createEvent(JmsEntityAction.CREATE);

        WorkerResult<Patient> result = (WorkerResult<Patient>) worker.processEvent(event);

        Patient returned = result.getEntity();
        WorkerStatusResult status = result.getStatus();

        assertThat(returned, Matchers.hasProperty("firstName", Matchers.is("Marek")));
        assertThat(returned, Matchers.hasProperty("id", Matchers.notNullValue()));
        assertThat(status, Matchers.is(WorkerStatusResult.OK));
    }

    private Event<PatientDto> createEvent(JmsEntityAction action){
        Event<PatientDto> event = new PatientEvent();
        PatientEntity entity = new PatientEntity();
        entity.setFirstName("Marek");
        entity.setLastName("Durovic");
        entity.setEmail("abc@inter.sk");
        entity.setBloodGroup(BloodGroup.AA);

        //event.setEntity(entity.createPatient());
        event.setAction(action);

        return event;
    }

}