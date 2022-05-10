package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import sk.durovic.exception.EntityIntegrationException;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JpaPersistWorker implements Runnable{

    private final ServiceContainer serviceContainer;
    private final List<? extends BaseEntityAbstractClass<?>> listOfEntities;
    private final boolean clearSaveContainer;

    JpaPersistWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities, boolean clearSaveContainer) {
        this.serviceContainer = new ServiceContainer();
        this.listOfEntities = listOfEntities;
        this.clearSaveContainer = clearSaveContainer;
    }

    ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    List<? extends BaseEntityAbstractClass<?>> getListOfEntities() {
        return listOfEntities;
    }

    @Override
    public void run() {
        List<? extends BaseEntityAbstractClass<?>> copyOfList = List.copyOf(listOfEntities);

        if (clearSaveContainer){
            listOfEntities.clear();
        }

        int previousSize;
        do {
            previousSize = copyOfList.size();

            Iterator<? extends BaseEntityAbstractClass<?>> it = copyOfList.listIterator();
            while (it.hasNext()) {
                try {
                    apply(it.next());
                    it.remove();
                } catch (EntityIntegrationException ignored) {
                    // iterate over all list to save parent entities
                }
            }

        } while(!copyOfList.isEmpty() && previousSize > copyOfList.size());

        if(copyOfList.size() > 0){
            //save to mongo to prevent loss of data
            log.debug(">>>>> Some of the entities can't be saved, size >> "+copyOfList.size());
        }


    }

    private <T extends BaseEntityAbstractClass<ID>, ID> void apply(T entity) throws EntityIntegrationException {
        try {
            execute(entity);
        } catch (ConstraintViolationException constraintViolationException){
            log.debug(":: Constraint violation of Entity :: ");
            log.debug(constraintViolationException.getConstraintViolations().toString());
            throw new EntityIntegrationException("Constraint violation of Entity");
        } catch (DataIntegrityViolationException dataIntegrityViolationException){
            log.debug(Arrays.toString(dataIntegrityViolationException.getStackTrace()));
            throw new EntityIntegrationException("Data integrity violation of Entity");
        }
    }

    <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity) {
        Optional<Service<T,ID,?>> service = serviceContainer.getService(entity.getClass());
        service.ifPresent(serv -> serv.save(entity));
    }
}