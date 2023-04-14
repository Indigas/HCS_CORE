package sk.durovic.mapper;

public interface EntityConverter2Dto<T, R> {

    R convert2Dto(T entity);
    T convert2Entity(R dto);
}
