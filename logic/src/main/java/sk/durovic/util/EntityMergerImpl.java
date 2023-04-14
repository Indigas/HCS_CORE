package sk.durovic.util;

import org.mapstruct.factory.Mappers;
import sk.durovic.mapper.*;
import sk.durovic.model.*;

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
        else if(aClass.isInstance(Diagnose.class))
            mapperClass = DiagnoseMapper.class;
        else if(aClass.isInstance(Disease.class))
            mapperClass = DiagnoseMapper.class;
        else if(aClass.isInstance(MedicalRecord.class))
            mapperClass = MedicalRecordMapper.class;
        else if(aClass.isInstance(Patient_Diagnose.class))
            mapperClass = PatientDiagnoseMapper.class;

        return (EntityMapper<T, R>) Mappers.getMapper(mapperClass);
    }
}
