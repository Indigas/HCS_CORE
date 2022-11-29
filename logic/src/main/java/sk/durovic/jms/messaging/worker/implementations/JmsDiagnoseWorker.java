package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Diagnose;

@Slf4j
public class JmsDiagnoseWorker extends JmsMessageWorkerService<Diagnose> {

    public static final String DIAGNOSE_QUEUE = "DIAGNOSE_QUEUE";


    public JmsDiagnoseWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<?> processEvent(Event<?> event) {
        return null;
    }
}
