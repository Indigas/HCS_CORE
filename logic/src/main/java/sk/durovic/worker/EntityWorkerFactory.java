package sk.durovic.worker;

import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.BaseEntityAbstractClass;

import java.io.Serializable;

public class EntityWorkerFactory {
    public static <T extends BaseEntityAbstractClass<ID>, ID> EntityWorker<T, ID> createEntityWorker(EntityManagerCreator creator){
        return new EntityWorkerImpl<>(creator);
    }
}
