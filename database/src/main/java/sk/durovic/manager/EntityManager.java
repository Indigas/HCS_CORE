package sk.durovic.manager;

import org.springframework.stereotype.Component;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;
import sk.durovic.worker.JpaPersistWorker;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class EntityManager {

    private EntityContainer entityContainer;
    private ServiceContainer serviceContainer;
    private ExecutorService executorService;

    public EntityManager() {
        this.entityContainer = new EntityContainer();
        this.serviceContainer = new ServiceContainer();
        this.executorService = Executors.newSingleThreadExecutor();
    }


    public <T extends BaseEntityAbstractClass<?>, ID> void save(T object) throws EntityChangeVersion {
        entityContainer.onSave(object);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityAbstractClass<ID>, ID> Optional<T> load(Class<T> clazz, ID id) throws ObjectIsNotEntityException {

        Optional<T> entity = (Optional<T>) entityContainer.onLoad(getReference(clazz, id));

        if(entity.isEmpty()) {
            entity = (Optional<T>) serviceContainer.getService(clazz).load(id);
            entity.ifPresent(entityContainer::addToContainer);
        }

        return entity;
    }

    public <T extends BaseEntityAbstractClass<?>> boolean contains(T entity) throws ObjectIsNotEntityException {
        return load(entity.getClass(), entity.getId()).isPresent();
    }

    public <T extends BaseEntityAbstractClass<?>> void lock(T entity){
        entityContainer.onLock(entity);

    }

    public <T extends BaseEntityAbstractClass<?>> void release(T entity){
        entityContainer.onRelease(entity);

    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityAbstractClass<?>> void refresh(T entity) throws ObjectIsNotEntityException {
        if(!entityContainer.onRefresh(entity))
            throw new IllegalArgumentException("Entity is not managed by manager. Save it first.");

        Optional<T> loadedEntity = load(entity.getClass(), entity.getId());
        loadedEntity.ifPresent(entityContainer::addToContainer);
    }

    public <T extends BaseEntityAbstractClass<?>> void remove(T entity){
        entityContainer.onRemove(entity);
    }

    // prerobit tak ako commit - cize sa zavola iny thread aby persistol entity
    public <T extends BaseEntityAbstractClass<?>> void flush(){
        // persist entities without clearing containers
        List<? extends BaseEntityAbstractClass<?>> toSave = entityContainer.onFlush();
        toSave.forEach(this::onFlush);
    }

    // suvisi s predoslou metodou
    @SuppressWarnings("unchecked")
    private <T> void onFlush(T object){
        executorService.execute(new JpaPersistWorker(entityContainer.onFlush(), false));
    }

    public void commit(){
        // call other thread to persist entities with status TO_SAVE, TO_REMOVE - clear containers
        executorService.execute(new JpaPersistWorker(entityContainer.onFlush(),true));
    }

    public void close(){
        clear();
        serviceContainer = null;
    }

    public void clear(){
        entityContainer = new EntityContainer();
    }


    public <T extends BaseEntityAbstractClass<ID>, ID> T getReference(Class<T> clazz, ID id) throws ObjectIsNotEntityException {
        try {
            T object = createEntity(clazz);
            EntityManipulator.setIdOfReferenceEntity(object, id);
            return object;
        } catch (Exception e){
            e.printStackTrace();
        }

        throw new ObjectIsNotEntityException("Class is not a Entity class. Cannot create reference for class :: " + clazz);
    }

    private <T> T createEntity(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }




}
