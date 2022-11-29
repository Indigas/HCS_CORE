package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.MedicalRecord;

@Slf4j
public class JmsMedicalRecordWorker extends JmsMessageWorkerService<MedicalRecord> {

    public static final String MEDIACAL_RECORD_QUEUE = "MEDIACAL_RECORD_QUEUE";


    public JmsMedicalRecordWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<?> processEvent(Event<?> event) {
        return null;
    }
}
