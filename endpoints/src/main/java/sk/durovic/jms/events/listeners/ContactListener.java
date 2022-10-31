package sk.durovic.jms.events.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import sk.durovic.jms.events.EntityListener;
import sk.durovic.jms.messaging.worker.implementations.JmsContactWorker;

import javax.jms.Message;

@Slf4j
public class ContactListener implements EntityListener {

    @JmsListener(destination = JmsContactWorker.CONTACT_QUEUE)
    @Override
    public void receiveMessage(Message msg) {
    }

    @Override
    public void receiveAndReplyMessage(Message msg) {

    }
}
