package sk.durovic.mapper;

public interface EntityMapper<T> {

    T updateEntity(T src, T dest);
}
