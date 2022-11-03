package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.model.Patient;

@Slf4j
public class JmsPatientWorker implements JmsMessageWorker<Patient> {

    public static final String PATIENT_QUEUE = "PATIENT_QUEUE";
    public static final String PATIENT_WITH_REPLY_QUEUE = "PATIENT_WITH_REPLY_QUEUE";

    @Override
    public void processMessage(Event<Patient> message) {
        log.info("Started processing JMS message");

        Patient patient = message.getEntity();
    }

    @Override
    public WorkerResult<Patient> processMessageWithReply(Event<Patient> message) {
        return null;
    }

}
