package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.entity.Patient_DiagnoseEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsPatient_DiagnoseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient_Diagnose;

import javax.jms.Message;
import java.util.List;

@Service
@Slf4j
public class Patient_DiagnoseListener extends EntityListener<Patient_Diagnose> {

    public Patient_DiagnoseListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.provider().createJmsPatient_DiagnoseWorker(ems));
    }

    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for Patient_Diagnose_QUEUE");

        defaultMessageProcessing(msg, Patient_DiagnoseEvent.class);
    }

}
