package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;

@Slf4j
public class JmsContactWorker extends JmsMessageWorkerService<Contact> {

    public static final String CONTACT_QUEUE = "CONTACT_QUEUE";


    public JmsContactWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<?> processEvent(Event<?> event) {
        return null;
    }
}
