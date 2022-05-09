package sk.durovic.manager;


import sk.durovic.exception.EntityChangeVersion;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EntityContainer {

    private final Container container;

    public EntityContainer() {
        this.container = new Container();
    }

    <T extends BaseEntityAbstractClass<?>, ID> Optional<T> onLoad(T entity){
        return container.load(entity);
    }

    <T extends BaseEntityAbstractClass<?>> void onSave(T entity) throws EntityChangeVersion {
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

    @SuppressWarnings("unchecked")
    public Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> onFlush() {
        Map<Version.Status, List<? extends BaseEntityAbstractClass<?>>> map = new HashMap<>();
        map.put(Version.Status.TO_SAVE, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.TO_SAVE));
        map.put(Version.Status.TO_REMOVE, (List<? extends BaseEntityAbstractClass<?>>) container.getByStatus(Version.Status.TO_REMOVE));

        return map;
    }

    public void clear(){
        container.clear();
    }
}
