package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;
import java.util.List;
import java.util.Optional;

/**
 * Worker to persist, update entities in DB
 */
@Slf4j
public class JpaPersistWorker extends JpaProcessWorker{

    public JpaPersistWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities, boolean clearSaveContainer) {
        super(listOfEntities, clearSaveContainer);
    }

    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity) {
        Optional<Service<T,ID,?>> service = getServiceContainer().getService(entity.getClass());
        service.ifPresent(serv -> serv.save(entity));
    }
}
