package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.model.Patient;

@Slf4j
public class JmsPatientWorker implements JmsMessageWorker {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";

    @SuppressWarnings("unchecked")
    @Override
    public void processMessage(Object message) {
        log.info("Started processing JMS message");

        Patient patient = ((Event<Patient>)message).getEntity();
    }

}
