package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Disease;

@Slf4j
public class JmsDiseaseWorker extends JmsEntityServiceWorker<Disease, Long> {

    public static final String DISEASE_QUEUE = "DISEASE_QUEUE";


    public JmsDiseaseWorker(EntityServiceManager ems) {
        super(ems);
    }


    @Override
    Disease updateEntity(Disease source, Disease dest) {
        return null;
    }
}
