package sk.durovic.manager;


import sk.durovic.collection.Entry;
import sk.durovic.collection.MultiEntry;
import sk.durovic.exception.EntityChangeVersion;
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
            , List<? extends BaseEntityAbstractClass<?>>> entityTable;


    Container(){
        entityTable = new HashMap<>();
    }

    <T extends BaseEntityAbstractClass<?>> void save(T entity) throws EntityChangeVersion {

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            if(entry.getKey().getValue() == entity.getClass()){

                Iterator<? extends BaseEntityAbstractClass<?>> it = entry.getValue().listIterator();
                while(it.hasNext()){
                    BaseEntityAbstractClass<?> ent = it.next();
                    Version version = ent.getVersion();

                    if (ent.equals(entity)) {

                        if (version.getVersion() == entity.getVersion().getVersion())
                            throw new EntityChangeVersion("Entity has same version as actually saved");
                        else if (version.getVersion() > entity.getVersion().getVersion())
                            throw new EntityChangeVersion("Entity has lower version as actually saved");

                        it.remove();

                    }
                    ent.getVersion().onSave();
                    getListOfEntities(ent.getVersion().getStatus(), ent.getClass()).add(ent);

                }
            }

        }

    }

    @SuppressWarnings("unchecked")
    <T extends BaseEntityAbstractClass<?>, ID> Optional<T> load(T entity){

        return (Optional<T>) getByClass(entity.getClass()).stream().filter(ent -> ent.equals(entity)).findFirst();
    }

    <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity){
        getListOfEntities(Version.Status.OPTIMISTIC_LOCK, entity.getClass()).add(entity);
    }

    <T extends BaseEntityAbstractClass<?>> boolean removeFromContainer(T entity){
        return getByClass(entity.getClass()).remove(entity);
    }

    <T extends BaseEntityAbstractClass<?>, ID> void onChangeStatus(T entity){
        getListOfEntities(entity.getVersion().getStatusOld(), entity.getClass()).remove(entity);
        getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
    }


    @SuppressWarnings("unchecked")
    List<? super BaseEntityAbstractClass<?>> getListOfEntities(Version.Status status, Class<?> clazz){
        Entry<Version.Status, Class<?>> entry = new MultiEntry<>();
        entry.put(status, clazz);
        return (List<? super BaseEntityAbstractClass<?>>) entityTable.get(entry);
    }

    List<? super BaseEntityAbstractClass<?>> getByStatus(Version.Status status){
        List<? super BaseEntityAbstractClass<?>> allWithStatus = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getKey() == status)
                allWithStatus.addAll(entry.getValue());
        }

        return allWithStatus;
    }

    @SuppressWarnings("unchecked")
    <T extends BaseEntityAbstractClass<?>> List<T> getByClass(Class<T> clazz){
        List<T> allWithClass = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getValue() == clazz)
                allWithClass.addAll((Collection<? extends T>) entry.getValue());
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
