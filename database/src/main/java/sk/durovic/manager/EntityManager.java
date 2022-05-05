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
            setIdOfReferenceEntity(object, id);
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

    @SuppressWarnings("unchecked")
    private <T, R extends BaseEntityAbstractClass<?>> Class<R> getEntityClass(T object)
            throws ObjectIsNotEntityException {

        Class<?> clazz = object.getClass();

        while(clazz != BaseEntityAbstractClass.class){
            if (clazz.isAnnotationPresent(Entity.class))
                return (Class<R>) clazz;

            clazz = clazz.getSuperclass();
        }

        throw new ObjectIsNotEntityException(object + " is not a Entity class");
    }


    private <T extends BaseEntityAbstractClass<ID>, ID> void setIdOfReferenceEntity(T object, ID id){
        Class<?> clazz = object.getClass();

        while(clazz != Object.class){
            try {
                Field field = clazz.getDeclaredField("id");
                field.setAccessible(true);
                field.set(object, id);
                clazz = clazz.getSuperclass();
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
