package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Disease;

import java.io.Serializable;

@Slf4j
public class JmsDiseaseWorker<T extends Serializable> extends JmsMessageWorkerService<Disease,T> {

    public static final String DISEASE_QUEUE = "DISEASE_QUEUE";


    public JmsDiseaseWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<T> processEvent(Event<T> event) {
        return null;
    }
}
