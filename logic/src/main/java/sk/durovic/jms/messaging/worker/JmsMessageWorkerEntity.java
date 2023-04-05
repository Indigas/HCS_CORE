package sk.durovic.jms.messaging.worker;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.jms.messaging.actions.JmsAction;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.EntityWorker;
import sk.durovic.worker.EntityWorkerFactory;

@Slf4j
public class JmsMessageWorkerEntity<T extends BaseEntityAbstractClass<ID>, ID> implements JmsMessageWorker<T, ID> {

    private final EntityManagerCreator creator;
    private final EntityWorker<T, ID> worker;

    public JmsMessageWorkerEntity(EntityManagerCreator creator) {
        this.creator = creator;
        this.worker = EntityWorkerFactory.createEntityWorker(creator);
    }

    protected EntityManagerCreator getCreator() {
        return creator;
    }

    @Override
    public void processActionOnEntity(T entity, JmsAction action) {

    }
}
