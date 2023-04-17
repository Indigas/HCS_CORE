package sk.durovic.mapper;

public interface EntityConverter<T, R> {

    T convert2Dto(R entity);
    R convert2Entity(T dto);
}
