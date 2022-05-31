package sk.durovic.manager.basic;

import org.springframework.context.ApplicationContext;
import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.manager.Container;
import sk.durovic.manager.EntityContainer;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.JpaProcessor;

import java.util.*;

/**
 * Class for calling methods on container based on events
 */
class EntityContainerBasic implements EntityContainer {

    private final Container container;

    public EntityContainerBasic() {
        this.container = new ContainerBasic();
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> Optional<T> onLoad(T entity){
        return container.load(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void onSave(T entity) {
        container.save(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity){
        container.addToContainer(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> boolean onRefresh(T entity){
        return container.removeFromContainer(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void onRemove(T entity){
        onSave(entity);
        entity.getVersion().toRemove();
        container.onChangeStatus(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void onLock(T entity) {
        onSave(entity);
        entity.getVersion().lock();
        container.onChangeStatus(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> void onRelease(T entity) {
        entity.getVersion().release();
        container.onChangeStatus(entity);
    }

    @Override
    public <T extends BaseEntityAbstractClass<?>> boolean contains(T entity) {
        return onLoad(entity).isPresent();
    }

    /**
     * called to perform worker tasks on entities, ec CRUD operations
     * @param context Application context to fetch services
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public JpaProcessor[] onFlush(ApplicationContext context){
        JpaProcessor[] processors = new JpaProcessor[]{
                Version.Status.TO_SAVE,
                Version.Status.TO_REMOVE,
                Version.Status.LOCK,
                Version.Status.OPTIMISTIC_LOCK
        };

        Arrays.stream(processors).forEach(status ->
                status.initialize( (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus((Version.Status)status),
                        context));

        return processors;
    }

    @Override
    public void clear(){
        container.clear();
    }
}
