package sk.durovic.worker;

import java.util.Collection;

public interface EntityWorker<T, ID> {

    Collection<T> save(Collection<T> entities);
    void delete(Collection<T> entities);
    void deleteById(ID id);
    void update(Collection<T> entity);
    void updateEntity(ID id, T entity);
    Collection<T> load(Collection<T> entity);
    T loadById(Class<T> clazz, ID id);
}
