package sk.durovic.jms.messaging.worker.provider;

import sk.durovic.jms.messaging.worker.JmsMessageWorkerEntity;
import sk.durovic.manager.factory.EntityManagerCreator;

import java.io.Serializable;

public interface JmsWorkerManagerProvider {

    public <T extends Serializable> JmsMessageWorkerEntity<T> createEntityWorker(EntityManagerCreator creator);
}
