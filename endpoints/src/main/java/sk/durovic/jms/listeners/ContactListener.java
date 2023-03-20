package sk.durovic.jms.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.events.entity.ContactEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Contact;

import javax.jms.Message;

@Service
@Slf4j
public class ContactListener extends EntityListener<Contact> {

    public ContactListener(JmsTemplate jmsTemplate, EntityManagerCreator creator) {
        super(jmsTemplate, JmsWorker.provider().createJmsContactWorker(creator));
    }

    @JmsListener(destination = JmsContactWorker.CONTACT_QUEUE, concurrency = "3-10")
    @Override
    public void receiveMessage(Message msg) {

        processMessage(msg, ContactEvent.class);
    }
}
