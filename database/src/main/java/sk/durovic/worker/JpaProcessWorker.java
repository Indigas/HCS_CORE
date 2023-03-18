package sk.durovic.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import sk.durovic.exception.EntityIntegrationException;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.manager.basic.ServiceContainerBasic;
import sk.durovic.model.BaseEntityAbstractClass;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract class for child workers. Child worker need to override execute method in order to perform desired task.
 */
@Slf4j
public abstract class JpaProcessWorker implements Runnable {

    private final ServiceContainer serviceContainer;
    private final List<? extends BaseEntityAbstractClass<?>> listOfEntities;

    JpaProcessWorker(List<? extends BaseEntityAbstractClass<?>> listOfEntities,
                     ApplicationContext context) {
        this.serviceContainer = new ServiceContainerBasic(context);
        this.listOfEntities = listOfEntities;
    }

    protected ServiceContainer getServiceContainer() {
        return serviceContainer;
    }

    protected List<? extends BaseEntityAbstractClass<?>> getListOfEntities() {
        return listOfEntities;
    }

    @Override
    public void run() {
        List<? extends BaseEntityAbstractClass<?>> copyOfList = new ArrayList<>(List.copyOf(listOfEntities));

        int previousSize;
        do {
            previousSize = copyOfList.size();

            Iterator<? extends BaseEntityAbstractClass<?>> it = copyOfList.iterator();
            while (it.hasNext()) {
                try {
                    apply(it.next());
                    it.remove();
                } catch (EntityIntegrationException ignored) {
                    // iterate over all list to save parent entities
                    // catch blok has only purpose, that method not crash when saving child entities
                    // so loop can go over and over again, while every entity is saved
                    // added condition, if something wrong is with entity, when entity cannot be saved
                    // it remains in collection and after that condition check if more entities were saved
                    // if not, stop loop and  log them, or save to later inspection
                }
            }

        } while(!copyOfList.isEmpty() && previousSize > copyOfList.size());

        if(copyOfList.size() > 0){
            //save to mongo to prevent loss of data
            log.debug(">>>>> Some of the entities can't be saved, size >> "+copyOfList.size());
            throw new EntityIntegrationException("Constraint/data integrity violation");
        }


    }

    /**
     * Method to catch exceptions and execute task
     * @param entity
     * @param <T>
     * @param <ID>
     * @throws EntityIntegrationException
     */
    private <T extends BaseEntityAbstractClass<ID>, ID> void apply(T entity) {
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

    /**
     * Method performing CRUD operations, or other processes based on entity status
     * @param entity
     * @param <T>
     * @param <ID>
     */
    abstract public <T extends BaseEntityAbstractClass<ID>, ID> void execute(T entity);
}
