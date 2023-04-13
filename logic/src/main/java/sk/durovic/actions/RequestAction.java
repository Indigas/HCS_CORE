package sk.durovic.actions;

import sk.durovic.model.BaseEntityAbstractClass;

import java.util.Collection;

public interface RequestAction<T, ID> {
    Collection<T> get(Collection<T> entities);
    Collection<T> post(Collection<T> entities);
    Collection<T> put(Collection<T> entities);
    void delete(Collection<T> entities);
}
