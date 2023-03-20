package sk.durovic.worker;

import sk.durovic.manager.factory.EntityManagerCreator;

import java.io.Serializable;

public class EntityWorkerFactory {
    public static <T extends Serializable, ID> EntityWorker<T, ID> createEntityWorker(EntityManagerCreator creator){
        return new EntityWorkerImpl<>(creator);
    }
}
