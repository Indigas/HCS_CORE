package sk.durovic.helper;

import sk.durovic.model.Patient_Diagnose;

import java.lang.reflect.Field;

public class Helper {

    public static void setIdOfInstance(Object instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException{
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
}
