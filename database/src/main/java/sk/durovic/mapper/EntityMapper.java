package sk.durovic.mapper;

import sk.durovic.model.*;
import sk.durovic.model.access.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class EntityMapper {

    private static Map<Class<?>, Class<?>> entityMap = new HashMap<>();

    static {
        entityMap.put(Patient.class, PatientEntity.class);
        entityMap.put(MedicalRecord.class, MedicalRecordEntity.class);
        entityMap.put(Disease.class, DiseaseEntity.class);
        entityMap.put(Diagnose.class, DiagnoseEntity.class);
        entityMap.put(Contact.class, ContactEntity.class);

    }

    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntityToPersist(T object) throws Exception{
        Class<?> clazz = object.getClass().getSuperclass();
        Constructor<R> constructor = (Constructor<R>) clazz.getDeclaredConstructor();

        return convertEntity(object, clazz, constructor);
    }

    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntity(T object) throws Exception{
        Class<?> clazz = object.getClass();
        Constructor<R> constructor = (Constructor<R>) entityMap.get(clazz).getDeclaredConstructor();

        return convertEntity(object, clazz, constructor);
    }

    private static <T, R extends BaseEntityAbstractClass<?>> R convertEntity(T object, Class<?> clazz, Constructor<R> constructor) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        constructor.setAccessible(true);
        R entity = constructor.newInstance();

        while(clazz != Object.class){
            Field[] fields = clazz.getDeclaredFields();

            for(Field field : fields){
                field.setAccessible(true);
                field.set(entity, field.get(object));
            }

            clazz = clazz.getSuperclass();
        }

        return entity;
    }

}
