package sk.durovic.jms.messaging.worker;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.jms.messaging.event.Event;
import sk.durovic.jms.messaging.worker.result.EntityWorkerResult;
import sk.durovic.jms.messaging.worker.result.WorkerResult;
import sk.durovic.manager.factory.EntityManagerCreator;

import java.io.Serializable;

@Slf4j
public class JmsMessageWorkerEntity<T extends Serializable> implements JmsMessageWorker<T> {

    private final EntityManagerCreator creator;

    public JmsMessageWorkerEntity(EntityManagerCreator creator) {
        this.creator = creator;
    }

    protected EntityManagerCreator getCreator() {
        return creator;
    }

    @Override
    public WorkerResult<T> processEvent(Event<T> event) {
        log.info("Started processing JMS message");
        WorkerResult<T> result = new EntityWorkerResult<>();

        if(!(event instanceof EntityEvent)) {
            return EntityWorkerResult.createBadEventResult(event);
        }

        return null;
    }
}
