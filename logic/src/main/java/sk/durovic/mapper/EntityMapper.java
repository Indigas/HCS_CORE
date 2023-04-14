package sk.durovic.mapper;

public interface EntityMapper<T, R> {

    R updateEntity(T src, R dest);
}
