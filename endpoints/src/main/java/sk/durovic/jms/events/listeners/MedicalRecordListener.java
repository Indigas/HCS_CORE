package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.model.MedicalRecord;

import javax.jms.Message;

@Service
@Slf4j
public class MedicalRecordListener extends EntityListener<MedicalRecord> {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";
    public MedicalRecordListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate);
    }

    @JmsListener(destination = MEDIACAL_RECORD_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for MEDIACAL_RECORD_QUEUE");

//        processMessage(msg, MediacalRecordEvent.class);
    }

}
