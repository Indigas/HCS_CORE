package sk.durovic.helper;

import sk.durovic.model.Diagnose;
import sk.durovic.model.Patient;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class EntityReferenceCreator {

    public static <T,ID> T convertIdToEntity(ID id, Class<T> clazz){
        T obj = null;
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            obj = constructor.newInstance();

            Field field = clazz.getDeclaredField("id");
            field.setAccessible(true);
            field.set(obj, id);

        } catch (Exception e){
            //aaa
        }

        return obj;
    }
}
