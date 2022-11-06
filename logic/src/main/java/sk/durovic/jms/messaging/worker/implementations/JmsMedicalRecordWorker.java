package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorker;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.EntityManager;
import sk.durovic.model.MedicalRecord;

@Slf4j
public class JmsMedicalRecordWorker implements JmsMessageWorker<MedicalRecord> {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";
    public static final String MEDIACAL_RECORD_WITH_REPLY_QUEUE = "MEDIACAL_RECORD_WITH_REPLY_QUEUE";

    private final EntityManager em;

    public JmsMedicalRecordWorker(EntityManager em) {
        this.em = em;
    }

    @Override
    public void processMessage(Event<MedicalRecord> message) {

    }

    @Override
    public WorkerResult<MedicalRecord> processMessageWithReply(Event<MedicalRecord> message) {
        return null;
    }
}
