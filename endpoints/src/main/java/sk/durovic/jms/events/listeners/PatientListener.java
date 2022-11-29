package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.entity.PatientEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient;

import javax.jms.Message;


@Service
@Slf4j
public class PatientListener extends EntityListener<Patient> {

    public PatientListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.provider().createJmsPatientWorker(ems));
    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg){

        defaultMessageProcessing(msg, PatientEvent.class);

    }

}
