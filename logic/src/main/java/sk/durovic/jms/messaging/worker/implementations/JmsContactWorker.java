package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;

@Slf4j
public class JmsContactWorker implements JmsMessageWorker<Contact> {

    public static final String CONTACT_QUEUE = "CONTACT_QUEUE";
    public static final String CONTACT_WITH_REPLY_QUEUE = "CONTACT_WITH_REPLY_QUEUE";

    private final EntityServiceManager ems;

    public JmsContactWorker(EntityServiceManager ems) {
        this.ems = ems;
    }

    @Override
    public void processMessage(Event<Contact> message) {
        log.info("Started processing JMS message");
    }

    @Override
    public WorkerResult<Contact> processMessageWithReply(Event<Contact> message) {
        return null;
    }
}
