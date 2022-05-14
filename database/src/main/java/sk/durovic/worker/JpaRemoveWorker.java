package sk.durovic.worker;

import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;

import java.util.List;
import java.util.Optional;

/**
 * Worker to remove entities from DB
 */
public class JpaRemoveWorker extends JpaProcessWorker{

    public JpaRemoveWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities) {
        super(listOfEntities, true);
    }

    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity) {
        Optional<Service<T,ID,?>> service = getServiceContainer().getService(entity.getClass());
        service.ifPresent(serv -> serv.deleteById(entity.getId()));
    }
}
