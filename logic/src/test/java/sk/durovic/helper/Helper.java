package sk.durovic.helper;

import java.lang.reflect.Field;

public class Helper {

    public static void setIdOfInstance(Object instance, Object value) throws IllegalAccessException{
        setField(instance, "id", value);
    }

    public static void setVersionOfInstance(Object instance, Long value) throws IllegalAccessException {
        setField(instance, "version", value);
    }

    private static void setField(Object instance, String name, Object value) throws IllegalAccessException{
        Class<?> clazz = instance.getClass();

        while(clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField("id");
                field.setAccessible(true);
                field.set(instance, value);
                clazz = clazz.getSuperclass();
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
    }
}
