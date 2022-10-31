package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;


@Slf4j
public class JmsPatient_DiagnoseWorker implements JmsMessageWorker {

    public static final String Patient_Diagnose_QUEUE = "Patient_Diagnose_QUEUE";

    @Override
    public void processMessage(Object message) {
        log.info("Started processing JMS message");
    }

    @Override
    public Object processMessageWithReply(Object message) {
        return null;
    }
}
