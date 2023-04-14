package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.model.MedicalRecord;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicalRecordMapper extends EntityMapper<MedicalRecordDto, MedicalRecord>, EntityConverter2Dto<MedicalRecordDto, MedicalRecord> {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "diagnose", ignore = true)
    @Override
    MedicalRecord updateEntity(MedicalRecordDto src, @MappingTarget MedicalRecord dest);
}
