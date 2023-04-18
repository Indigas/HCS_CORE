package sk.durovic.helper;

import org.mapstruct.factory.Mappers;
import sk.durovic.dto.*;
import sk.durovic.mapper.*;
import sk.durovic.model.*;

public class EntityMapperHelper {

    @SuppressWarnings("unchecked")
    public static <R, T> EntityMapper<T,R> getMapper(Class<?> aClass) {
        Class<?> mapperClass = ContactMapper.class;

        if (aClass.isInstance(Patient.class) || aClass.isInstance(PatientDto.class))
            mapperClass = PatientMapper.class;
        else if(aClass.isInstance(Diagnose.class) || aClass.isInstance(DiagnoseDto.class))
            mapperClass = DiagnoseMapper.class;
        else if(aClass.isInstance(Disease.class) || aClass.isInstance(DiseaseDto.class))
            mapperClass = DiagnoseMapper.class;
        else if(aClass.isInstance(MedicalRecord.class) || aClass.isInstance(MedicalRecordDto.class))
            mapperClass = MedicalRecordMapper.class;
        else if(aClass.isInstance(Patient_Diagnose.class) || aClass.isInstance(Patient_DiagnoseDTO.class))
            mapperClass = PatientDiagnoseMapper.class;

        return (EntityMapper<T, R>) Mappers.getMapper(mapperClass);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> EntityConverter<T, R> getConverter(Class<?> aClass){
        return (EntityConverter<T, R>) getMapper(aClass);
    }
}
