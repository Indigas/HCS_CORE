package sk.durovic.manager.basic;

import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;

import javax.persistence.Entity;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Helper methods to get entity class or setId of referenced entity
 */
public class EntityManipulator {

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

    /**
     * Set id of entity, which is not stored in container
     * @param object
     * @param id
     * @param <T>
     * @param <ID>
     */
    static <T extends BaseEntityAbstractClass<ID>, ID> void setIdOfReferenceEntity(T object, ID id){
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
