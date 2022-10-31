package sk.durovic.manager;

import sk.durovic.model.BaseEntityAbstractClass;

import java.util.Optional;

public interface ServiceContainer {

    <T extends BaseEntityAbstractClass<?>, R> Optional<R> getService(Class<T> clazz);
    Object getObjectService(Class<?> clazz);
    void clear();
}
