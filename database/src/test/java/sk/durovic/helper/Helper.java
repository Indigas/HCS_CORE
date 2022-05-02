package sk.durovic.helper;

import sk.durovic.model.Patient_Diagnose;

import java.lang.reflect.Field;

public class Helper {

    /*public static void setIdOfInstance(Class clazz, Object instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException{
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }*/

    public static void setIdOfInstance(Object instance, Object value) throws IllegalAccessException{
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
