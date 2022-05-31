package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.model.Patient;
import sk.durovic.service.PatientService;
import sk.durovic.service.Service;
import java.util.List;
import java.util.Optional;

/**
 * Worker to persist, update entities in DB
 */
@Slf4j
public class JpaPersistWorker extends JpaProcessWorker {

    public JpaPersistWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities,
                             ApplicationContext context) {
        super(listOfEntities, context);
    }

    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity) {
        Optional<Service<T,ID,?>> service = getServiceContainer().getService(entity.getClass());
        service.ifPresent(serv -> serv.save(entity));
    }

}
