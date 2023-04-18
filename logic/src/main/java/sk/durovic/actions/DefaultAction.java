package sk.durovic.actions;

import sk.durovic.helper.EntityMergerHelper;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;
import sk.durovic.util.EntityMerger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultAction<T extends BaseEntityAbstractClass<ID>, ID> implements RequestAction<T,ID>{

    protected final EntityService<T,ID,?> service;

    public DefaultAction(EntityService<T, ID, ?> service) {
        this.service = service;
    }


    @Override
    public Collection<T> get(Collection<T> entities) {
        return StreamSupport.stream(service.loadAll(extractIds(entities)).spliterator(),false)
                .collect(Collectors.toList());
    }

    private Iterable<ID> extractIds(Collection<T> entities) {
        return entities.stream().map(BaseEntityAbstractClass::getId).collect(Collectors.toList());
    }

    @Override
    public Collection<T> post(Collection<T> entities) {
        return StreamSupport.stream(service.saveAll(entities).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Collection<T> put(Collection<T> entities) {
        Map<ID, T> loadedEntities = new HashMap<>();
        get(entities).forEach(entity -> loadedEntities.put(entity.getId(), entity));
        Collection<T> entitiesToSave = new ArrayList<>();

        for(T entity : entities){
            T mergedEntity = mergeEntity(entity, loadedEntities.get(entity.getId()));
            entitiesToSave.add(mergedEntity);
        }

        return StreamSupport.stream(service.saveAll(entitiesToSave).spliterator(), false).collect(Collectors.toList());
    }

    private T mergeEntity(T srcEntity, T dstEntity) {
        EntityMerger merger = EntityMergerHelper.getMergerForEntity(srcEntity.getClass());
        return merger.merge(srcEntity, dstEntity);
    }


    @Override
    public void delete(Collection<T> entities) {
        service.deleteAll(entities);
    }
}
