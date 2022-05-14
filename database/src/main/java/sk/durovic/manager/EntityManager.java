package sk.durovic.manager;

import org.springframework.stereotype.Component;
import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;
import sk.durovic.worker.JpaWorkers;

import java.io.Closeable;
import java.lang.reflect.Constructor;
import java.util.Optional;

@Component
public class EntityManager implements Closeable {

    private final EntityContainer entityContainer;
    private final ServiceContainer serviceContainer;
    private final JpaWorkers jpaWorkers;

    public EntityManager() {
        this.entityContainer = new EntityContainer();
        this.serviceContainer = new ServiceContainer();
        this.jpaWorkers = new JpaWorkers();
    }


    public <T extends BaseEntityAbstractClass<?>> void save(T object) {
        entityContainer.onSave(object);
    }

    public <T extends BaseEntityAbstractClass<ID>, ID> Optional<T> load(Class<T> clazz, ID id) {

        Optional<T> entity = entityContainer.onLoad(getReference(clazz, id));

        if(entity.isEmpty()) {
            Optional<Service<T, ID, ?>> service = serviceContainer.getService(clazz);
            entity = service.isPresent() ? service.get().load(id) : Optional.empty();
            entity.ifPresent(entityContainer::addToContainer);
        }

        return entity;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityAbstractClass<?>> boolean contains(T entity) {
        return load(entity.getClass(), entity.getId()).isPresent();
    }

    public <T extends BaseEntityAbstractClass<?>> void lock(T entity){
        entityContainer.onLock(entity);

    }

    public <T extends BaseEntityAbstractClass<?>> void release(T entity){
        entityContainer.onRelease(entity);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityAbstractClass<?>> void refresh(T entity) {
        if(!entityContainer.onRefresh(entity))
            throw new IllegalArgumentException("Entity is not managed by manager. Save it first.");

        Optional<T> loadedEntity = load(entity.getClass(), entity.getId());
        loadedEntity.ifPresent(entityContainer::addToContainer);
    }

    public <T extends BaseEntityAbstractClass<?>> void remove(T entity){
        entityContainer.onRemove(entity);
    }

    public <T extends BaseEntityAbstractClass<?>> void flush(){
        jpaWorkers.execute(entityContainer.onFlush(false));
    }

    public void commit(){
        jpaWorkers.execute(entityContainer.onFlush(true));
    }

    @Override
    public void close(){
        jpaWorkers.close();
        clear();
    }

    public void clear(){
        entityContainer.clear();
        serviceContainer.clear();
    }


    public <T extends BaseEntityAbstractClass<ID>, ID> T getReference(Class<T> clazz, ID id){
        if (id == null)
            throw new NullPointerException("ID is null");

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
