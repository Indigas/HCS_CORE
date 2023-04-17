package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.PatientDto;
import sk.durovic.model.Patient;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper extends EntityMapper<PatientDto, Patient>, EntityConverter<PatientDto, Patient> {

    @Override
    Patient updateEntity(PatientDto src, @MappingTarget Patient dest);
}
