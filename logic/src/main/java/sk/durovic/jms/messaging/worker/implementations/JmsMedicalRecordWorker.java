package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;

@Slf4j
public class JmsMedicalRecordWorker implements JmsMessageWorker {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";
    @Override
    public void processMessage(Object message) {
        log.info("Started processing JMS message");
    }
}
