package sk.durovic.worker;

import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.Collection;

class EntityWorkerImpl<T extends BaseEntityAbstractClass<ID>, ID> implements EntityWorker<T, ID> {

    private final EntityManagerCreator creator;

    public EntityWorkerImpl(EntityManagerCreator creator) {
        this.creator = creator;
    }

    @Override
    public Collection<T> save(Collection<T> entities) {
        return null;
    }

    @Override
    public void delete(Collection<T> entities) {
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void update(Collection<T> entity) {

    }

    @Override
    public void updateEntity(ID id, T entity) {

    }

    @Override
    public Collection<T> load(Collection<T> entity) {
        return null;
    }

    @Override
    public T loadById(Class<T> clazz, ID id) {
        return creator.getBasicEntityManager().load(clazz, id).orElse(null);
    }
}
