package sk.durovic.jms.messaging.worker.provider.utility;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerEntity;
import sk.durovic.jms.messaging.worker.provider.JmsWorkerManagerProvider;
import sk.durovic.manager.factory.EntityManagerCreator;

import java.io.Serializable;

public class JmsWorkerManagerProviderImpl implements JmsWorkerManagerProvider {

    @Override
    public <T extends Serializable> JmsMessageWorkerEntity<T> createEntityWorker(EntityManagerCreator creator) {
        return new JmsMessageWorkerEntity<>(creator);
    }
}
