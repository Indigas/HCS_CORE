package sk.durovic.mapper;

import sk.durovic.manager.EntityManipulator;
import sk.durovic.model.*;
import sk.durovic.model.access.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper class. Used to map entities from mutable to immutable form and vice versa.
 */
public class EntityMapper {

    private static Map<Class<?>, Class<?>> entityMap = new HashMap<>();

    static {
        entityMap.put(Patient.class, PatientEntity.class);
        entityMap.put(MedicalRecord.class, MedicalRecordEntity.class);
        entityMap.put(Disease.class, DiseaseEntity.class);
        entityMap.put(Diagnose.class, DiagnoseEntity.class);
        entityMap.put(Contact.class, ContactEntity.class);

    }

    /**
     * Change entity from mutable to immutable form, which can be persisted.
     * @param object
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntityToPersist(T object)
            throws Exception {
        //Class<?> clazz = object.getClass().getSuperclass();
        Class<?> clazz = EntityManipulator.getEntityClass(object);
        Constructor<R> constructor = (Constructor<R>) clazz.getDeclaredConstructor();

        return convertEntity(object, clazz, constructor);
    }

    /**
     * Convert entity to it's mutable form
     * @param object
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntity(T object) throws Exception{
        Class<?> clazz = object.getClass();
        Constructor<R> constructor = (Constructor<R>) entityMap.get(clazz).getDeclaredConstructor();

        return convertEntity(object, clazz, constructor);
    }

    /**
     * Copy fields to existing entity
     * @param source
     * @param destination
     * @param <T>
     * @param <U>
     * @throws IllegalAccessException
     */
    public static <T, U extends T> void mapToExistingObject(U source, T destination) throws IllegalAccessException {

        setFields(source,  destination);
    }

    /**
     * Convert entity
     * @param object
     * @param clazz
     * @param constructor
     * @param <T>
     * @param <R>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private static <T, R extends BaseEntityAbstractClass<?>> R convertEntity(T object, Class<?> clazz, Constructor<R> constructor)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        constructor.setAccessible(true);
        R entity = constructor.newInstance();

        setFields(object, entity);

        return entity;
    }

    /**
     * Copy fields with java reflection
     * @param source
     * @param destination
     * @param <T>
     * @param <R>
     * @throws IllegalAccessException
     */
    private static <T, R> void setFields(T source,  R destination) throws IllegalAccessException {
        Class<?> clazz = destination.getClass();

        while(clazz != Object.class){
            Field[] fields = clazz.getDeclaredFields();

            for(Field field : fields){
                field.setAccessible(true);
                field.set(destination, field.get(source));
            }

            clazz = clazz.getSuperclass();
        }
    }

}
