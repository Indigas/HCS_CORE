package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.model.Patient;

import javax.jms.Message;


@Service
@Slf4j
public class PatientListener extends EntityListener<Patient> {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";

    public PatientListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @JmsListener(destination = PATIENT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg){

//        processMessage(msg, PatientEvent.class);

    }

}
