package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;

@Slf4j
public class JmsContactWorker extends JmsEntityServiceWorker<Contact, Long> {

    public static final String CONTACT_QUEUE = "CONTACT_QUEUE";

    public JmsContactWorker(EntityServiceManager ems) {
        super(ems);
    }


    @Override
    Contact updateEntity(Contact source, Contact dest) {
        return null;
    }
}
