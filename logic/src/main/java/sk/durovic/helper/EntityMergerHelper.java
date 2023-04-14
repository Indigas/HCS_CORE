package sk.durovic.helper;

import sk.durovic.util.EntityMerger;
import sk.durovic.util.EntityMergerImpl;

public class EntityMergerHelper {

    public static EntityMerger getMergerForEntity(Class<?> clazz){
       return new EntityMergerImpl();
    }
}
