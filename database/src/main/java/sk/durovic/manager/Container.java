package sk.durovic.manager;


import sk.durovic.collection.Entry;
import sk.durovic.collection.MultiEntry;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.model.*;

import java.util.*;

/**
 * Hold entities in predefined map
 */
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

    /**
     * Save entity to container
     * @param entity
     * @param <T>
     */
    <T extends BaseEntityAbstractClass<?>> void save(T entity) {

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            if(entry.getKey().getValue() == entity.getClass()){

                Iterator<? extends BaseEntityAbstractClass<?>> it = entry.getValue().listIterator();
                while(it.hasNext()){
                    BaseEntityAbstractClass<?> ent = it.next();
                    Version version = ent.getVersion();

                    if(entity.getId() == null){
                        entity.getVersion().onSave();
                        getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
                        return;
                    }

                    if (ent.equals(entity)) {
                        if(!ent.getVersion().isReadyForChange())
                            throw new EntityChangeVersion("Entity is locked or marked to remove");

                        int entityVersion = entity.getVersion().getVersion()+1;

                        if (version.getVersion() == entityVersion)
                            throw new EntityChangeVersion("Entity has same version as actually saved");
                        else if (version.getVersion() > entityVersion)
                            throw new EntityChangeVersion("Entity has lower version as actually saved");

                        it.remove();
                        entity.getVersion().onSave();
                        getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
                        return;
                    }

                }
            }

        }

    }

    @SuppressWarnings("unchecked")
    <T extends BaseEntityAbstractClass<ID>, ID> Optional<T> load(T entity){
        return (Optional<T>) getByClass(entity.getClass()).stream().filter(ent -> ent.equals(entity)).findFirst();
    }

    <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity){
        getListOfEntities(Version.Status.OPTIMISTIC_LOCK, entity.getClass()).add(entity);
    }

    <T extends BaseEntityAbstractClass<?>> boolean removeFromContainer(T entity){
        return getByClass(entity.getClass()).remove(entity);
    }

    /**
     * Called if entity status is changed
     * @param entity
     * @param <T>
     */
    <T extends BaseEntityAbstractClass<?>> void onChangeStatus(T entity){
        getListOfEntities(entity.getVersion().getStatusOld(), entity.getClass()).remove(entity);
        getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
    }

    /**
     * get list of entities based on status and class
     * @param status searching status
     * @param clazz desired class
     * @return
     */
    @SuppressWarnings("unchecked")
    List<? super BaseEntityAbstractClass<?>> getListOfEntities(Version.Status status, Class<?> clazz){
        Entry<Version.Status, Class<?>> entry = new MultiEntry<>();
        entry.put(status, clazz);
        return  (List<? super BaseEntityAbstractClass<?>>)entityTable.get(entry);
    }

    /**
     * get list of entities based on status
     * @param status
     * @return
     */
    List<? super BaseEntityAbstractClass<?>> getByStatus(Version.Status status){
        List<? super BaseEntityAbstractClass<?>> allWithStatus = new LinkedList<>();

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            if (entry.getKey().getKey() == status)
                allWithStatus.addAll(entry.getValue());
        }

        return allWithStatus;
    }

    /**
     * get list of entities based on class
     * @param clazz
     * @param <T>
     * @param <ID>
     * @return
     */
    @SuppressWarnings("unchecked")
    <T extends BaseEntityAbstractClass<ID>, ID> List<T> getByClass(Class<T> clazz){
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

    void clear(){
        entityTable.clear();
    }
}
