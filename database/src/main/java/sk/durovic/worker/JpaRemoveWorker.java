package sk.durovic.worker;

import org.springframework.context.ApplicationContext;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Worker to remove entities from DB
 */
public class JpaRemoveWorker extends JpaProcessWorker{

    public JpaRemoveWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities, ApplicationContext context)
    {
        super(listOfEntities, context);
    }

    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity) {
        Optional<EntityService<T,ID,?>> service = getServiceContainer().getService(entity.getClass());
        service.ifPresent(serv -> serv.deleteById(entity.getId()));
    }
}
