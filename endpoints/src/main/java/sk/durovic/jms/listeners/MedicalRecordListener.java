package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.jms.events.entity.MediacalRecordEvent;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.jms.messaging.worker.service.JmsMedicalRecordWorker;
import sk.durovic.manager.service.EntityServiceManager;

import javax.jms.Message;

@Service
@Slf4j
public class MedicalRecordListener extends EntityListener<MedicalRecordDto> {

    public MedicalRecordListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.serviceProvider().createJmsMedicalRecordWorker(ems));
    }

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for MEDIACAL_RECORD_QUEUE");

        processMessage(msg, MediacalRecordEvent.class);
    }

}
