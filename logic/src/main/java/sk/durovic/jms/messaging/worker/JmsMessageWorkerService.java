package sk.durovic.jms.messaging.worker;

import sk.durovic.jms.messaging.actions.JmsAction;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Patient;
import sk.durovic.service.EntityService;
import sk.durovic.service.PatientEntityService;

import java.util.Optional;

public class JmsMessageWorkerService<T, ID> implements JmsMessageWorker<T, ID>{
    private final EntityServiceManager ems;
    private final EntityService<T,ID,?> service;

    @SuppressWarnings("unchecked")
    public JmsMessageWorkerService(EntityServiceManager ems) {
        this.ems = ems;

        Optional<PatientEntityService> optional = ems.getServiceContainer().getService(Patient.class);
        this.service = (EntityService<T, ID, ?>) optional.orElse(null);
    }

    protected EntityService<T,ID,?> getService(){
        return service;
    }


    @Override
    public void processActionOnEntity(T entity, JmsAction action) {

    }
}
