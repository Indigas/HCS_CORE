package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;

@Slf4j
public class JmsContactWorker implements JmsMessageWorker {

    public static final String CONTACT_QUEUE = "CONTACT_QUEUE";
    @Override
    public void processMessage(Object message) {
        log.info("Started processing JMS message");
    }
}
