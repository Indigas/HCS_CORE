package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerFactory;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Diagnose;

@Slf4j
public class JmsDiagnoseWorker implements JmsMessageWorker<Diagnose> {

    public static final String DIAGNOSE_QUEUE = "DIAGNOSE_QUEUE";
    public static final String DIAGNOSE_WITH_REPLY_QUEUE = "DIAGNOSE_WITH_REPLY_QUEUE";

    private final EntityManager em;

    public JmsDiagnoseWorker(EntityManager em) {
        this.em = em;
    }

    @Override
    public void processMessage(Event<Diagnose> message) {

    }

    @Override
    public WorkerResult<Diagnose> processMessageWithReply(Event<Diagnose> message) {
        return null;
    }
}
