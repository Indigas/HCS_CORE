package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import sk.durovic.jms.converter.JmsMessage2Event;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.event.ContactEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;
import sk.durovic.jms.messaging.worker.provider.utility.JmsWorker;
import sk.durovic.model.Contact;
import sk.durovic.worker.JmsWorkerTask;

import javax.jms.Message;
import java.util.Optional;

@Slf4j
public class ContactListener implements EntityListener {

    @JmsListener(destination = JmsContactWorker.CONTACT_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
        JmsMessageWorker worker = JmsWorker.provider().createJmsContactWorker();

        Optional<Event<Contact>> result = JmsMessage2Event.convertMsg2Event(msg, ContactEvent.class);

        result.ifPresent(contactEvent -> JmsWorkerTask.processWithoutReply(worker::processMessage, contactEvent));
    }
}
