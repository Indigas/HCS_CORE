package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.PatientDto;
import sk.durovic.model.Patient;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper extends EntityMapper<Patient>, EntityConverter<PatientDto, Patient> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "diseases", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    Patient updateEntity(Patient src, @MappingTarget Patient dest);

    @Override
    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "diseases", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    Patient convert2Entity(PatientDto dto);
}
