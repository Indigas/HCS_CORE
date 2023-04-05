package sk.durovic.jms.messaging.worker.service;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.worker.JmsMessageWorkerService;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.BaseEntityAbstractClass;

@Slf4j
public abstract class JmsEntityServiceWorker<T extends BaseEntityAbstractClass<?>, ID>
        extends JmsMessageWorkerService<T, ID> {

    protected JmsEntityServiceWorker(EntityServiceManager ems){
        super(ems);
    }


    //    @Override
//    public WorkerResult<S> processEvent(Event<S> event) {
//        return null;
//        log.info("Started processing JMS message");
//        WorkerResult<S> result = new EntityWorkerResult<>();
//
//        S entity = (S) event.getEntity();
//        JmsEntityAction action = (JmsEntityAction) event.getAction();
//        Optional<S> loadedEntity = Optional.empty();
//
//        R service = (R) getService();

//        switch (action){
//            case GET:
//                loadedEntity = service.load(entity.getId());
//                if(loadedEntity.isPresent()){
//                    result.setEntity(loadedEntity.get());
//                    result.setStatus(WorkerStatusResult.OK);
//                } else
//                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);
//
//                break;
//            case CREATE:
//                entity = service.save(entity);
//
//                result.setEntity(entity);
//                result.setStatus(WorkerStatusResult.OK);
//                break;
//            case UPDATE:
//                loadedEntity = service.load(entity.getId());
//
//                if (loadedEntity.isPresent()) {
//                    T updatedEntity = updateEntity(entity, loadedEntity.get());
//
//                    service.save(updatedEntity);
//
//                    result.setStatus(WorkerStatusResult.OK);
//                } else
//                    result.setStatus(WorkerStatusResult.ENTITY_NOT_EXISTS);
//
//                break;
//            case DELETE:
//                service.delete(entity);
//
//                result.setStatus(WorkerStatusResult.OK);
//                break;
//        }
//
//        return result;
//    }

    abstract T updateEntity(T source, T dest);
}
