package sk.durovic.manager;

import org.springframework.stereotype.Component;
import sk.durovic.annotations.EntityProcessor;
import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;

import javax.persistence.Entity;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Component
public class EntityManager {

    private final EntityContainer entityContainer;
    private final ServiceContainer serviceContainer;

    public EntityManager() {
        this.entityContainer = new EntityContainer();
        this.serviceContainer = new ServiceContainer();
    }


    <T extends BaseEntityAbstractClass<?>> T save(T object){
        Service<T,?,?> service = serviceContainer.getService(object.getClass());
        return service.save(object);
    }


    public <T extends BaseEntityAbstractClass<ID>, ID> T getReference(Class<T> clazz, ID id) throws ObjectIsNotEntityException {
        try {
            T object = createEntity(clazz);
            EntityManipulator.setIdOfReferenceEntity(object, id);
            return object;
        } catch (Exception e){
            e.printStackTrace();
        }

        throw new ObjectIsNotEntityException("Class is not a Entity class. Cannot create reference for class :: " + clazz);
    }

    private <T> T createEntity(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }



}
