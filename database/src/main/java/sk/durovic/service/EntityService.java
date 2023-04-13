package sk.durovic.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Skeleton of service classes
 * @param <T> entity
 * @param <ID> id of given entity
 * @param <R> repository of given entity
 */
public abstract class EntityService<T, ID, R extends CrudRepository<T, ID>> {

    protected R repo;

    public T save(T object){
        return repo.save(object);
    }

    public void delete(T object){
        repo.delete(object);
    }

    public void deleteById(ID id){
        repo.deleteById(id);
    }

    public Optional<T> load(ID id){
        return repo.findById(id);
    }

    public Iterable<T> loadAll(Iterable<ID> ids){
        return repo.findAllById(ids);
    }

    public Iterable<T> saveAll(Iterable<T> objects){
        return repo.saveAll(objects);
    }

    public void deleteAll(Iterable<T> objects){
        repo.deleteAll(objects);
    }

    public void deleteAllById(Iterable<ID> ids){
        repo.deleteAllById(ids);
    }

}
