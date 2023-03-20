package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerEntity;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;
import sk.durovic.model.Diagnose;

import java.io.Serializable;

@Slf4j
public class JmsContactWorker<T extends Serializable> extends JmsMessageWorkerService<Contact, T> {

    public static final String CONTACT_QUEUE = "CONTACT_QUEUE";

    public JmsContactWorker(EntityServiceManager ems) {
        super(ems);
    }


    @Override
    public WorkerResult<T> processEvent(Event<T> event) {
        return null;
    }
}
