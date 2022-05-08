package sk.durovic.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class Service<T, ID, R extends CrudRepository<T, ID>> {

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


}
