package sk.durovic.manager;

import sk.durovic.model.BaseEntityAbstractClass;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Optional;

public interface EntityManager extends Closeable {

    <T extends BaseEntityAbstractClass<?>> void save(T object);
    <T extends BaseEntityAbstractClass<ID>, ID extends Serializable> Optional<T> load(Class<T> clazz, ID id);
    <T extends BaseEntityAbstractClass<ID>, ID extends Serializable> T getReference(Class<T> clazz, ID id);
    <T extends BaseEntityAbstractClass<?>> boolean contains(T entity);
    <T extends BaseEntityAbstractClass<?>> void lock(T entity);
    <T extends BaseEntityAbstractClass<?>> void release(T entity);
    <T extends BaseEntityAbstractClass<?>> void refresh(T entity);
    <T extends BaseEntityAbstractClass<?>> void remove(T entity);
    <T extends BaseEntityAbstractClass<?>> void removeFromContainer(T entity);
    void flush();
    void commit();
    void clear();
}
