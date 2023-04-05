package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerEntity;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.BaseEntityAbstractClass;

public interface JmsWorkerManagerProvider {

    public <T extends BaseEntityAbstractClass<ID>, ID> JmsMessageWorkerEntity<T, ID> createEntityWorker(EntityManagerCreator creator);
}
