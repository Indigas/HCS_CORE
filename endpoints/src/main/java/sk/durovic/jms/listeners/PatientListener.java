package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.PatientDto;
import sk.durovic.jms.events.entity.PatientEvent;
import sk.durovic.jms.messaging.worker.service.JmsPatientWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;

import javax.jms.Message;


@Service
@Slf4j
public class PatientListener extends EntityListener<PatientDto> {

    public PatientListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.serviceProvider().createJmsPatientWorker(ems));
    }

    @JmsListener(destination = JmsPatientWorker.PATIENT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg){

        processMessage(msg, PatientEvent.class);

    }

}
