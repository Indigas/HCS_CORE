package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Diagnose;

@Slf4j
public class JmsDiagnoseWorker extends JmsEntityServiceWorker<Diagnose, Long> {

    public static final String DIAGNOSE_QUEUE = "DIAGNOSE_QUEUE";


    public JmsDiagnoseWorker(EntityServiceManager ems) {
        super(ems);
    }


    @Override
    Diagnose updateEntity(Diagnose source, Diagnose dest) {
        return null;
    }
}
