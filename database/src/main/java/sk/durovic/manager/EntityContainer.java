package sk.durovic.manager;

import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.worker.JpaProcessor;

import java.util.*;

public class EntityContainer {

    private final Container container;

    public EntityContainer() {
        this.container = new Container();
    }

    <T extends BaseEntityAbstractClass<ID>, ID> Optional<T> onLoad(T entity){
        return container.load(entity);
    }

    <T extends BaseEntityAbstractClass<?>> void onSave(T entity) {
        container.save(entity);
    }

    <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity){
        container.addToContainer(entity);
    }

    <T extends BaseEntityAbstractClass<?>> boolean onRefresh(T entity){
        return container.removeFromContainer(entity);
    }

    <T extends BaseEntityAbstractClass<?>> void onRemove(T entity){
        entity.getVersion().toRemove();
        container.onChangeStatus(entity);
    }


    public <T extends BaseEntityAbstractClass<?>> void onLock(T entity) {
        entity.getVersion().lock();
        container.onChangeStatus(entity);
    }

    public <T extends BaseEntityAbstractClass<?>> void onRelease(T entity) {
        entity.getVersion().release();
        container.onChangeStatus(entity);
    }
/*
    @SuppressWarnings("unchecked")
    public Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> onFlush() {
        Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> map = new HashMap<>();
        map.put(Version.Status.TO_SAVE, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.TO_SAVE));
        map.put(Version.Status.TO_REMOVE, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.TO_REMOVE));
        map.put(Version.Status.LOCK, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.LOCK));
        map.put(Version.Status.OPTIMISTIC_LOCK, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.OPTIMISTIC_LOCK));

        return map;
    }
*/
    @SuppressWarnings("unchecked")
    public JpaProcessor[] onFlush(boolean clearContainer){
        JpaProcessor[] processors = new JpaProcessor[]{
                Version.Status.TO_SAVE,
                Version.Status.TO_REMOVE,
                Version.Status.LOCK,
                Version.Status.OPTIMISTIC_LOCK
        };

        Arrays.stream(processors).forEach(status ->
                status.initialize( (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus((Version.Status)status), clearContainer));

        return processors;
    }

    public void clear(){
        container.clear();
    }
}
