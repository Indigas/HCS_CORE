package sk.durovic.mapper;

import sk.durovic.dto.EntityDto;

public interface EntityConverter<T, R> {

    T convert2Dto(R entity);
    R convert2Entity(T dto);
}
