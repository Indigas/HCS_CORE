package sk.durovic.helper;

import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;

import javax.persistence.Entity;
import java.lang.reflect.Field;

/**
 * Helper methods to get entity class or setId of referenced entity
 */
public class EntityManipulatorHelper {

    /**
     * Return entity class of object. Class has to be annotated with Entity annotation
     * @param object
     * @param <T>
     * @param <R>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> Class<R> getEntityClass(T object) {

        Class<?> clazz = object.getClass();

        while(clazz != BaseEntityAbstractClass.class){
            if (clazz.isAnnotationPresent(Entity.class))
                return (Class<R>) clazz;

            clazz = clazz.getSuperclass();
        }

        throw new ObjectIsNotEntityException(object + " is not a Entity class");
    }

}
