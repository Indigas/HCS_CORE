package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsDiseaseWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Disease;

import javax.jms.Message;

@Service
@Slf4j
public class DiseaseListener extends EntityListener<Disease> {

    public DiseaseListener(JmsTemplate jmsTemplate, EntityManagerFactory factory) {
        super(jmsTemplate, JmsWorker.provider().createJmsDiseaseWorker(factory.getBasicEntityManager()));
    }

    @JmsListener(destination = JmsDiseaseWorker.DISEASE_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @JmsListener(destination = JmsDiseaseWorker.DISEASE_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
