package sk.durovic.helper;

import org.mapstruct.factory.Mappers;
import sk.durovic.dto.*;
import sk.durovic.mapper.*;
import sk.durovic.model.*;

public class EntityMapperHelper {

    @SuppressWarnings("unchecked")
    public static <R, T> EntityMapper<T,R> getMapper(Class<?> aClass) {
        Class<?> mapperClass = ContactMapper.class;

        if (aClass == Patient.class || aClass == PatientDto.class)
            mapperClass = PatientMapper.class;
        else if(aClass == Diagnose.class || aClass == DiagnoseDto.class)
            mapperClass = DiagnoseMapper.class;
        else if(aClass == Disease.class || aClass == DiseaseDto.class)
            mapperClass = DiagnoseMapper.class;
        else if(aClass == MedicalRecord.class || aClass == MedicalRecordDto.class)
            mapperClass = MedicalRecordMapper.class;
        else if(aClass == Patient_Diagnose.class || aClass == Patient_DiagnoseDTO.class)
            mapperClass = PatientDiagnoseMapper.class;

        return (EntityMapper<T, R>) Mappers.getMapper(mapperClass);
    }

    @SuppressWarnings("unchecked")
    public static <T, R> EntityConverter<T, R> getConverter(Class<?> aClass){
        return (EntityConverter<T, R>) getMapper(aClass);
    }
}
