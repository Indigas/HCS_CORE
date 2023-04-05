package sk.durovic.jms.messaging.worker.adapter;

import java.util.Collection;

public interface JmsWorker2EntityWorker {

    <T> Collection<T> save(Collection<T> entities);
    <T> void delete(Collection<T> entities);
    <ID> void deleteById(ID id);
    <T> void update(Collection<T> entity);
    <T,ID> void updateEntity(ID id, T entity);
    <T> Collection<T> load(Collection<T> entity);
    <T,ID> T loadById(Class<T> clazz, ID id);
}
