package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Diagnose;
import sk.durovic.service.DiagnoseEntityService;

import java.io.Serializable;

@Slf4j
public class JmsDiagnoseWorker<T extends Serializable> extends JmsEntityServiceWorker<Diagnose, DiagnoseEntityService,T> {

    public static final String DIAGNOSE_QUEUE = "DIAGNOSE_QUEUE";


    public JmsDiagnoseWorker(EntityServiceManager ems) {
        super(ems);
    }

    @Override
    public WorkerResult<T> processEvent(Event<T> event) {
        return null;
    }

    @Override
    Diagnose updateEntity(Diagnose source, Diagnose dest) {
        return null;
    }
}
