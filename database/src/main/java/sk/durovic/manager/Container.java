package sk.durovic.manager;

import sk.durovic.manager.basic.Version;
import sk.durovic.model.BaseEntityAbstractClass;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Container {

     <T extends BaseEntityAbstractClass<?>> void save(T entity);
     <T extends BaseEntityAbstractClass<?>> Optional<T> load(T entity);
     <T extends BaseEntityAbstractClass<?>> void addToContainer(T entity);
     <T extends BaseEntityAbstractClass<?>> boolean removeFromContainer(T entity);
     <T extends BaseEntityAbstractClass<?>> void onChangeStatus(T entity);
     List<? super BaseEntityAbstractClass<?>> getListOfEntities(Version.Status status, Class<?> clazz);
     List<? super BaseEntityAbstractClass<?>> getByStatus(Version.Status status);
     <T extends BaseEntityAbstractClass<ID>, ID extends Serializable> List<T> getByClass(Class<T> clazz);
     void clear();
}
