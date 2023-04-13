package sk.durovic.mapper;

import lombok.extern.slf4j.Slf4j;
import sk.durovic.helper.EntityManipulatorHelper;
import sk.durovic.model.BaseEntityAbstractClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper class. Used to map entities from mutable to immutable form and vice versa.
 */
@Slf4j
public class EntityMapper {

    private static Map<Class<?>, Class<?>> entityMap = new HashMap<>();

    static {
//        entityMap.put(Patient.class, PatientEntity.class);
//        entityMap.put(MedicalRecord.class, MedicalRecordEntity.class);
//        entityMap.put(Disease.class, DiseaseEntity.class);
//        entityMap.put(Diagnose.class, DiagnoseEntity.class);
//        entityMap.put(Contact.class, ContactEntity.class);

    }

    /**
     * Change entity from mutable to immutable form, which can be persisted.
     * @param object Mutable entity convert to immutable entity able to be persisted
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntityToPersist(T object)
            {
        //Class<?> clazz = object.getClass().getSuperclass();
        Class<?> clazz = EntityManipulatorHelper.getEntityClass(object);
        try {
            Constructor<R> constructor = (Constructor<R>) clazz.getDeclaredConstructor();

            return convertEntity(object, clazz, constructor);
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("Error while converting object to entity: "+object.getClass());
    }

    /**
     * Convert entity to it's mutable form
     * @param object
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntity(T object) {
        Class<?> clazz = object.getClass();
        try {
            Constructor<R> constructor = (Constructor<R>) entityMap.get(clazz).getDeclaredConstructor();

            return convertEntity(object, clazz, constructor);
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("Error while converting object to mutable entity: "+object.getClass());
    }

    /**
     * Copy fields to existing entity. Using destination fields to be set up from source.
     * Use destination as super class for source.
     * @param source
     * @param destination
     * @throws IllegalAccessException
     */
    public static <T, U extends T> void mapToExistingObject(U source, T destination) {
        try {
            setFields(source, destination);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Convert entity
     * @param object
     * @param clazz
     * @param constructor
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
