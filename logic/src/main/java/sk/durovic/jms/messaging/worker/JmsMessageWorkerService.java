package sk.durovic.jms.messaging.worker;

import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient;
import sk.durovic.service.EntityService;
import sk.durovic.service.PatientEntityService;

import java.io.Serializable;
import java.util.Optional;

public abstract class JmsMessageWorkerService<T, R extends Serializable> implements JmsMessageWorker<R>{
    private final EntityServiceManager ems;
    private final EntityService<T,?,?> service;

    @SuppressWarnings("unchecked")
    protected JmsMessageWorkerService(EntityServiceManager ems) {
        this.ems = ems;

        Optional<PatientEntityService> optional = ems.getServiceContainer().getService(Patient.class);
        this.service = (EntityService<T, ?, ?>) optional.orElse(null);
    }

    protected EntityService<T,?,?> getService(){
        return service;
    }


}
