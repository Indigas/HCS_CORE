package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Disease;

@Slf4j
public class JmsDiseaseWorker extends JmsMessageWorkerService<Disease> {

    public static final String DISEASE_QUEUE = "DISEASE_QUEUE";


    public JmsDiseaseWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<?> processEvent(Event<?> event) {
        return null;
    }
}
