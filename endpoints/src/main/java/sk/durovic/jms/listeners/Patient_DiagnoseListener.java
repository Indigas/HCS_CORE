package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.Patient_DiagnoseDTO;
import sk.durovic.jms.events.entity.Patient_DiagnoseEvent;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.jms.messaging.worker.service.JmsPatient_DiagnoseWorker;
import sk.durovic.manager.service.EntityServiceManager;

import javax.jms.Message;

@Service
@Slf4j
public class Patient_DiagnoseListener extends EntityListener<Patient_DiagnoseDTO> {

    public Patient_DiagnoseListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.serviceProvider().createJmsPatient_DiagnoseWorker(ems));
    }

    @JmsListener(destination = JmsPatient_DiagnoseWorker.Patient_Diagnose_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for Patient_Diagnose_QUEUE");

        processMessage(msg, Patient_DiagnoseEvent.class);
    }

}
