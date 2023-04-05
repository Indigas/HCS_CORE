package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerEntity;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerManagerProvider;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.BaseEntityAbstractClass;


public class JmsWorkerManagerProviderImpl implements JmsWorkerManagerProvider {


    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> JmsMessageWorkerEntity<T, ID> createEntityWorker(EntityManagerCreator creator) {
        return new JmsMessageWorkerEntity<>(creator);
    }
}
