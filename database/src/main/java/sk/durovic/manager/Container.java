package sk.durovic.manager;


import sk.durovic.collection.Entry;
import sk.durovic.collection.MultiEntry;
import sk.durovic.model.*;

import java.util.*;

class Container {

    private final Class<?>[] clazzes =
            new Class[]{
                    Patient.class,
                    MedicalRecord.class,
                    Disease.class,
                    Diagnose.class,
                    Contact.class
                        };

    private final Map<Entry<Version.Status, Class<?>>
            , List<Entry<Class<?>, Version>>> entityTable;

    Container(){
        entityTable = new HashMap<>();
    }

    private void initEntityTable(){
        List<Entry<Version.Status, Class<?>>> keys = initTableKeys();

        keys.forEach(key -> entityTable.put(key, new LinkedList<>()));
    }

    private List<Entry<Version.Status, Class<?>>> initTableKeys(){
        List<Entry<Version.Status, Class<?>>> keys = new ArrayList<>();

        for (Version.Status status : Version.Status.values()){
            for (Class<?> clazz : clazzes){
                MultiEntry<Version.Status, Class<?>> entry = new MultiEntry<>();
                entry.put(status, clazz);
            }
        }

        return keys;
    }

    List<Entry<Class<?>, Version>> getValue(Entry<Version.Status, Class<?>> entry){
        return entityTable.get(entry);
    }

    List<Entry<Class<?>, Version>> getValue(Version.Status status, Class<?> clazz){
        Entry<Version.Status, Class<?>> entry = new Entry<>();
        entry.put(status, clazz);
        return entityTable.get(entry);
    }

    List<Entry<Class<?>, Version>> getByStatus(Version.Status status){
        List<Entry<Class<?>, Version>> allWithStatus = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<Entry<Class<?>, Version>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getKey() == status)
                allWithStatus.addAll(entry.getValue());
        }

        return allWithStatus;
    }

    List<Entry<Class<?>, Version>> getByClass(Class<?> clazz){
        List<Entry<Class<?>, Version>> allWithClass = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<Entry<Class<?>, Version>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getValue() == clazz)
                allWithClass.addAll(entry.getValue());
        }

        return allWithClass;
    }

}
