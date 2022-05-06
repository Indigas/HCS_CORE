package sk.durovic.manager;


import sk.durovic.collection.Entry;
import sk.durovic.collection.MultiEntry;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.model.*;
import sk.durovic.model.access.PatientEntity;

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
            , List<Entry<? extends BaseEntityAbstractClass<?>, Version>>> entityTable;


    Container(){
        entityTable = new HashMap<>();
    }

    <T extends BaseEntityAbstractClass<?>> void save(Entry<T, Version> entity) throws EntityChangeVersion {
        List<Entry<? extends BaseEntityAbstractClass<?>, Version>> entitiesToAdd = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<Entry<? extends BaseEntityAbstractClass<?>, Version>>> entry :
                entityTable.entrySet()) {

            if(entry.getKey().getValue() == entity.getClass()){

                Iterator<Entry<? extends BaseEntityAbstractClass<?>, Version>> it = entry.getValue().listIterator();
                while(it.hasNext()){
                    Entry<? extends BaseEntityAbstractClass<?>, Version> ent = it.next();
                    Version version = ent.getValue();

                    if (ent.getKey().equals(entity.getKey())) {
                        int versionNo = version.getVersion();

                        if (versionNo == entity.getValue().getVersion())
                            throw new EntityChangeVersion("Entity has same version as actually saved");
                        else if (versionNo > entity.getValue().getVersion())
                            throw new EntityChangeVersion("Entity has lower version as actually saved");

                        it.remove();
                    }

                    entitiesToAdd.add(entity);

                }
            }

        }

        entitiesToAdd.stream().forEach(entry -> getListOfEntities(entry.getValue().getStatus(), entry.getKey().getClass()).add(entry));

    }

    /*List<Entry<BaseEntityAbstractClass<?>, Version>> getListOfEntities(Entry<Version.Status, Class<?>> entry){
        return entityTable.get(entry);
    }*/

    List<Entry<? extends BaseEntityAbstractClass<?>, Version>> getListOfEntities(Version.Status status, Class<?> clazz){
        Entry<Version.Status, Class<?>> entry = new MultiEntry<>();
        entry.put(status, clazz);
        return entityTable.get(entry);
    }

    List<Entry<? extends BaseEntityAbstractClass<?>, Version>> getByStatus(Version.Status status){
        List<Entry<? extends BaseEntityAbstractClass<?>, Version>> allWithStatus = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<Entry<? extends BaseEntityAbstractClass<?>, Version>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getKey() == status)
                allWithStatus.addAll(entry.getValue());
        }

        return allWithStatus;
    }

    List<Entry<? extends BaseEntityAbstractClass<?>, Version>> getByClass(Class<?> clazz){
        List<Entry<? extends BaseEntityAbstractClass<?>, Version>> allWithClass = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<Entry<? extends BaseEntityAbstractClass<?>, Version>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getValue() == clazz)
                allWithClass.addAll(entry.getValue());
        }

        return allWithClass;
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

}
