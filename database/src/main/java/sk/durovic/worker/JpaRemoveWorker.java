package sk.durovic.worker;

import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;

public class JpaRemoveWorker extends JpaPersistWorker{

    JpaRemoveWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities) {
        super(listOfEntities, true);
    }

    @Override
    <T extends BaseEntityAbstractClass<?>> void execute(T entity) {
        getServiceContainer().getService(entity.getClass()).deleteById(entity.getId());
    }
}
