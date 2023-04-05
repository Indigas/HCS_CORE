package sk.durovic.jms.messaging.worker.adapter;

import sk.durovic.worker.EntityWorker;

import java.util.Collection;

public class JmsEntityWorkerAdapter implements JmsWorker2EntityWorker{

    private final EntityWorker<?,?> worker;

    public JmsEntityWorkerAdapter(EntityWorker<?,?> worker) {
        this.worker = worker;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Collection<T> save(Collection<T> entities) {
        ((EntityWorker<T,?>)worker).save(entities);
        return null;
    }

    @Override
    public <T> void delete(Collection<T> entities) {

    }

    @Override
    public <ID> void deleteById(ID id) {
    }

    @Override
    public <T> void update(Collection<T> entity) {

    }

    @Override
    public <T, ID> void updateEntity(ID id, T entity) {

    }

    @Override
    public <T> Collection<T> load(Collection<T> entity) {
        return null;
    }

    @Override
    public <T, ID> T loadById(Class<T> clazz, ID id) {
        return null;
    }
}
