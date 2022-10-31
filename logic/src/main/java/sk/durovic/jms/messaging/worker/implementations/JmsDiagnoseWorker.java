package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;

@Slf4j
public class JmsDiagnoseWorker implements JmsMessageWorker {

    public static final String DIAGNOSE_QUEUE = "DIAGNOSE_QUEUE";
    @Override
    public void processMessage(Object message) {
        log.info("Started processing JMS message");
    }

    @Override
    public Object processMessageWithReply(Object message) {
        return null;
    }
}
