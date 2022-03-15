package sk.durovic.service;

public interface Service<T, ID> {

    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
