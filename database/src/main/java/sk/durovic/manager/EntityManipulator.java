package sk.durovic.manager;

import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;

import javax.persistence.Entity;
import java.lang.reflect.Field;

public class EntityManipulator {

    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> Class<R> getEntityClass(T object)
            throws ObjectIsNotEntityException {

        Class<?> clazz = object.getClass();

        while(clazz != BaseEntityAbstractClass.class){
            if (clazz.isAnnotationPresent(Entity.class))
                return (Class<R>) clazz;

            clazz = clazz.getSuperclass();
        }

        throw new ObjectIsNotEntityException(object + " is not a Entity class");
    }


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
