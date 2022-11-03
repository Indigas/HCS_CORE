package sk.durovic.jms.events.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Contact;

import javax.jms.Message;

@Service
@Slf4j
public class ContactListener extends EntityListener<Contact> {

    protected ContactListener(JmsTemplate jmsTemplate) {
        super(jmsTemplate, JmsWorker.provider().createJmsContactWorker());
    }

    @JmsListener(destination = JmsContactWorker.CONTACT_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
