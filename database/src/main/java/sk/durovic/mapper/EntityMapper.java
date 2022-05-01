package sk.durovic.mapper;

import sk.durovic.model.BaseEntityAbstractClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class EntityMapper {

    @SuppressWarnings("unchecked")
    public static <T, R extends BaseEntityAbstractClass<?>> R mapEntity(T object) throws Exception{
        Class<?> clazz = object.getClass().getSuperclass();
        Constructor<R> constructor = (Constructor<R>) clazz.getDeclaredConstructor();
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
