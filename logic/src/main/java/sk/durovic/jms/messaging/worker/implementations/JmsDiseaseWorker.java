package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.EntityManager;
import sk.durovic.model.Disease;

@Slf4j
public class JmsDiseaseWorker implements JmsMessageWorker<Disease> {

    public static final String DISEASE_QUEUE = "DISEASE_QUEUE";
    public static final String DISEASE_WITH_REPLY_QUEUE = "DISEASE_WITH_REPLY_QUEUE";

    private final EntityManager em;

    public JmsDiseaseWorker(EntityManager em) {
        this.em = em;
    }

    @Override
    public void processMessage(Event<Disease> message) {

    }

    @Override
    public WorkerResult<Disease> processMessageWithReply(Event<Disease> message) {
        return null;
    }
}
