package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.entity.MediacalRecordEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsMedicalRecordWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.MedicalRecord;

import javax.jms.Message;

@Service
@Slf4j
public class MedicalRecordListener extends EntityListener<MedicalRecord> {

    public MedicalRecordListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.provider().createJmsMedicalRecordWorker(ems));
    }

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for MEDIACAL_RECORD_QUEUE");

        defaultMessageProcessing(msg, MediacalRecordEvent.class);
    }

}
