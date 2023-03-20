package sk.durovic.worker;

import sk.durovic.manager.factory.EntityManagerCreator;

import java.io.Serializable;
import java.util.Collection;

class EntityWorkerImpl<T extends Serializable, ID> implements EntityWorker<T, ID> {

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
    public T loadById(ID id) {
        return null;
    }
}
