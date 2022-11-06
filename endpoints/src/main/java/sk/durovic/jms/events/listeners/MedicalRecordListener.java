package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsMedicalRecordWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.MedicalRecord;

import javax.jms.Message;

@Service
@Slf4j
public class MedicalRecordListener extends EntityListener<MedicalRecord> {

    public MedicalRecordListener(JmsTemplate jmsTemplate, EntityManagerFactory factory) {
        super(jmsTemplate, JmsWorker.provider().createJmsMedicalRecordWorker(factory.getBasicEntityManager()));
    }

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @JmsListener(destination = JmsMedicalRecordWorker.MEDIACAL_RECORD_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
