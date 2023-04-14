package sk.durovic.util;

import org.mapstruct.factory.Mappers;
import sk.durovic.mapper.ContactMapper;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.mapper.PatientMapper;
import sk.durovic.model.Contact;
import sk.durovic.model.Patient;

public class EntityMergerImpl implements EntityMerger{

    @Override
    public <T, R> R merge(T src, R dst) {
        EntityMapper<T, R> mapper = getMapper(dst.getClass());

        return mapper.updateEntity(src, dst);
    }

    @SuppressWarnings("unchecked")
    private <R, T> EntityMapper<T,R> getMapper(Class<?> aClass) {
        Class<?> mapperClass = ContactMapper.class;

        if (aClass.isInstance(Patient.class))
            mapperClass = PatientMapper.class;


        return (EntityMapper<T, R>) Mappers.getMapper(mapperClass);
    }
}
