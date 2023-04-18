package sk.durovic.actions;

import org.springframework.data.domain.Example;
import sk.durovic.helper.EntityMergerHelper;
import sk.durovic.model.BaseEntityAbstractClass;
import sk.durovic.service.EntityService;
import sk.durovic.util.EntityMerger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultRequestAction<T extends BaseEntityAbstractClass<ID>, ID> implements RequestAction<T,ID>{

    protected final EntityService<T,ID,?> service;

    public DefaultRequestAction(EntityService<T, ID, ?> service) {
        this.service = service;
    }


    @Override
    public Collection<T> get(Collection<T> entities) {
        if(!entities.iterator().hasNext())
            return Collections.emptyList();

        List<T> withId = new ArrayList<>();
        List<T> withoutId = new ArrayList<>();

        Iterator<T> it = entities.iterator();
        while(it.hasNext()){
            T entity = it.next();

            if(entity.getId() == null)
                withoutId.add(entity);
            else
                withId.add(entity);
        }

        Set<T> loadedContacts = new HashSet<>();

        if(!withId.isEmpty())
            loadedContacts.addAll(loadEntities(withId));

        if(!withoutId.isEmpty())
            addContactsByPatient(withoutId, loadedContacts);

        return loadedContacts;
    }

    private Collection<T> loadEntities(Collection<T> entities){
        return StreamSupport.stream(service.loadAll(extractIds(entities)).spliterator(),false)
                .collect(Collectors.toList());
    }

    private Iterable<ID> extractIds(Collection<T> entities) {
        return entities.stream().map(BaseEntityAbstractClass::getId).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void addContactsByPatient(Collection<T> entities, Collection<T> loadedEntities){
        Set<String> parentIds = new HashSet<>();

        List<T> filteredContacts = entities.stream()
                .filter(entity -> !hasToBeFiltered(entity, parentIds)).collect(Collectors.toList());

        filteredContacts.forEach(contact -> loadContactsToList(contact, loadedEntities));
    }

    private boolean hasToBeFiltered(T entity, Collection<String> parentIds){
        if(entity.getParentId() == null)
            return true;

        String id = entity.getParentId();

        if(parentIds.contains(id)){
            return true;
        }

        parentIds.add(id);
        return false;

    }

    private void loadContactsToList(T entity, Collection<T> loadedEntities){
        Example<T> example = Example.of(entity);

        loadedEntities.addAll(
                StreamSupport.stream(
                                service.findAllByExample(example).spliterator(), false)
                        .collect(Collectors.toList()));
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
