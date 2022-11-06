package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.event.entity.ContactEvent;
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
@Slf4j
public class ContactListener extends EntityListener<Contact> {

    public ContactListener(JmsTemplate jmsTemplate, EntityServiceManager ems) {
        super(jmsTemplate, JmsWorker.provider().createJmsContactWorker(ems));
    }

    @JmsListener(destination = JmsContactWorker.CONTACT_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        log.info("Received JMS message for CONTACT_QUEUE");

        Event<Contact> result = JmsMessage2Event.convertMsg2Event(msg, ContactEvent.class);

        if(result.isResultOk())
            getMessageProcessor().processMessage(result);
    }

    @JmsListener(destination = JmsContactWorker.CONTACT_WITH_REPLY_QUEUE)
    @Override
    public void receiveAndReplyMessage(Message msg) {
        log.info("Received JMS message for CONTACT_WITH_REPLY_QUEUE");

        Event<Contact> result = JmsMessage2Event.convertMsg2Event(msg, ContactEvent.class);

        if(result.isResultOk()) {
            try {
                getMessageProcessor().processMessageAndReply(result, msg.getJMSReplyTo());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
