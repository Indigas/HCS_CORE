package sk.durovic.manager.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import sk.durovic.exception.ObjectIsNotEntityException;
import sk.durovic.manager.EntityContainer;
import sk.durovic.manager.EntityManager;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.Service;
import sk.durovic.worker.JpaWorkers;

import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * Class provide methods to use CRUD operations on entity and manage them.
 */
@Slf4j
@Component
public class EntityManagerBasic implements EntityManager {

    private final EntityContainer entityContainer;
    private final ServiceContainerBasic serviceContainer;
    private final JpaWorkers jpaWorkers;
    private final ApplicationContext context;

    public EntityManagerBasic(ApplicationContext context) {
        this.entityContainer = new EntityContainerBasic();
        this.serviceContainer = new ServiceContainerBasic(context);
        this.jpaWorkers = new JpaWorkers();
        this.context = context;
    }

    /**
     * Entity is being managed by manager
     * @param object
     */
    @Override
    public <T extends BaseEntityAbstractClass<?>> void save(T object) {
        entityContainer.onSave(object);
    }

    /**
     * load entity from DB, if not in container, or load from container
     * @param clazz class of entity
     * @param id ID of entity
     * @return
     */
    @Override
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
    @Override
    public <T extends BaseEntityAbstractClass<?>> boolean contains(T entity) {
        return entityContainer.contains(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void lock(T entity){
        entityContainer.onLock(entity);

    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void release(T entity){
        entityContainer.onRelease(entity);
    }

    /**
     * Reload entity from DB to container. Can't be used to load it from DB, when entity is not present in container
     * @param entity
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends BaseEntityAbstractClass<?>> void refresh(T entity) {
        if(!entityContainer.onRefresh(entity))
            throw new IllegalArgumentException("Entity is not managed by manager. Save it first.");

        Optional<T> loadedEntity = load(entity.getClass(), entity.getId());
        log.debug("Refresh action on Entity: "+entity.getClass()+" with ID: "+entity.getId());
        loadedEntity.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void remove(T entity){
        entityContainer.onRemove(entity);
    }

    /**
     * Perform task on saved entities without clearing container
     */
    @Override
    public void flush(){
        jpaWorkers.execute(entityContainer.onFlush(context));
    }

    /**
     * Perform task on saved entities and clear container
     */
    @Override
    public void commit(){
        jpaWorkers.execute(entityContainer.onFlush(context));
        entityContainer.clear();
    }

    @Override
    public void close(){
        jpaWorkers.close();
        clear();
    }

    @Override
    public void clear(){
        entityContainer.clear();
        serviceContainer.clear();
    }


    /**
     * Get reference of parent entity for child entity. Parent is not loaded from DB.
     * Only reference is created in order to save child entity.
     * @param clazz
     * @param id
     * @return
     */
    @Override
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

    private <T extends BaseEntityAbstractClass<?>> T createEntity(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
    }




}
