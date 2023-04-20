package sk.durovic.actions;

import java.util.Collection;

public interface RequestAction<T> {
    Collection<T> get(Collection<T> entities);
    Collection<T> post(Collection<T> entities);
    Collection<T> put(Collection<T> entities);
    void delete(Collection<T> entities);
}
