package sk.durovic.manager.basic;


import sk.durovic.collection.Entry;
import sk.durovic.collection.MultiEntry;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.exception.EntityLocked;
import sk.durovic.manager.Container;
import sk.durovic.manager.basic.Version;
import sk.durovic.model.*;

import java.util.*;

/**
 * Hold entities in predefined map
 */
class ContainerBasic implements Container {

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


    ContainerBasic(){
        entityTable = new HashMap<>();
        initEntityTable();
    }

    /**
     * Save entity to container
     * @param entity
     * @param <T>
     */
    @Override
    public <T extends BaseEntityAbstractClass<?>> void save(T entity) {

        for (Map.Entry<Entry<Version.Status, Class<?>>, List<? extends BaseEntityAbstractClass<?>>> entry :
                entityTable.entrySet()) {

            Entry<Version.Status, Class<?>> entityEntry = new MultiEntry<>(entity.getVersion().getStatus(), entity.getClass());
            if(//entry.getKey().getValue() == entity.getClass()
                    entry.getKey().equals(entityEntry)){

                Iterator<? extends BaseEntityAbstractClass<?>> it = entry.getValue().listIterator();
                while(it.hasNext()){
                    BaseEntityAbstractClass<?> ent = it.next();
                    Version version = ent.getVersion();

                    if(entity.getId() == null){
                        saveEntityToContainer(entity);
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
                        saveEntityToContainer(entity);
                        return;
                    }

                }

                saveEntityToContainer(entity);
                return;

            }

        }

    }

    private <T extends BaseEntityAbstractClass<?>> void saveEntityToContainer(T entity) {
        entity.getVersion().onSave();
        getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEntityAbstractClass<?>> Optional<T> load(T entity){
        return (Optional<T>) getByClass(entity.getClass()).stream().filter(ent -> ent.equals(entity)).findFirst();
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity){
        getListOfEntities(Version.Status.OPTIMISTIC_LOCK, entity.getClass()).add(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> boolean removeFromContainer(T entity){
        for (Version.Status status : Version.Status.values()) {
            if(getListOfEntities(status, entity.getClass()).remove(entity))
                return true;
        }
        return false;
    }

    /**
     * Called if entity status is changed
     * @param entity
     * @param <T>
     */
    @Override
    public <T extends BaseEntityAbstractClass<?>> void onChangeStatus(T entity){
        if(!entity.getVersion().isStatusChanged())
            return;

        if(getListOfEntities(entity.getVersion().getStatusOld(), entity.getClass()).remove(entity))
            getListOfEntities(entity.getVersion().getStatus(), entity.getClass()).add(entity);
        else
            throw new RuntimeException("Entity is not present in container. >>"+entity.getClass()+
                    " and ID: " +entity.getId());
    }

    /**
     * get list of entities based on status and class
     * @param status searching status
     * @param clazz desired class
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<? super BaseEntityAbstractClass<?>> getListOfEntities(Version.Status status, Class<?> clazz){
        Entry<Version.Status, Class<?>> entry = new MultiEntry<>();
        entry.put(status, clazz);
        return  (List<? super BaseEntityAbstractClass<?>>)entityTable.get(entry);
    }

    /**
     * get list of entities based on status
     * @param status
     * @return
     */
    @Override
    public List<? super BaseEntityAbstractClass<?>> getByStatus(Version.Status status){
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
    @Override
    public <T extends BaseEntityAbstractClass<ID>, ID> List<T> getByClass(Class<T> clazz){
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
                keys.add(entry);
            }
        }

        return keys;
    }

    public void clear(){
        entityTable.clear();
        initEntityTable();
    }
}
