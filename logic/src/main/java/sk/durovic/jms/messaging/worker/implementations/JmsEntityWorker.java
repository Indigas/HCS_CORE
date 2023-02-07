package sk.durovic.jms.messaging.worker.implementations;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.actions.JmsEntityAction;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.jms.messaging.worker.result.status.WorkerStatusResult;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.BaseEntity;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.service.EntityService;
import sk.durovic.service.PatientEntityService;

import java.util.Optional;

@Slf4j
public abstract class JmsEntityWorker<T extends BaseEntity, R extends EntityService<T, Long, ?>> extends JmsMessageWorkerService<T> {

    protected JmsEntityWorker(EntityServiceManager ems){
        super(ems);
    }

    @SuppressWarnings("unchecked")
    @Override
    public WorkerResult<T> processEvent(Event<?> event) {
        log.info("Started processing JMS message");
        WorkerResult<T> result = new EntityWorkerResult<>();

        T entity = (T) event.getEntity();
        JmsEntityAction action = (JmsEntityAction) event.getAction();
        Optional<T> loadedEntity = Optional.empty();

        R service = (R) getService();

        switch (action){
            case GET:
                loadedEntity = service.load(entity.getId());
                if(loadedEntity.isPresent()){
                    result.setEntity(loadedEntity.get());
                    result.setStatus(WorkerStatusResult.OK);
                } else
                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);

                break;
            case CREATE:
                entity = service.save(entity);

                result.setEntity(entity);
                result.setStatus(WorkerStatusResult.OK);
                break;
            case UPDATE:
                loadedEntity = service.load(entity.getId());

                if (loadedEntity.isPresent()) {
                    T updatedEntity = updateEntity(entity, loadedEntity.get());

                    service.save(updatedEntity);

                    result.setStatus(WorkerStatusResult.OK);
                } else
                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);

                break;
            case DELETE:
                service.delete(entity);

                result.setStatus(WorkerStatusResult.OK);
                break;
        }

        return result;
    }

    abstract T updateEntity(T source, T dest);
}
