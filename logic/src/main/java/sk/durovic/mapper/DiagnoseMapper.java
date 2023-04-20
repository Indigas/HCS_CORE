package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.DiagnoseDto;
import sk.durovic.model.Diagnose;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiagnoseMapper extends EntityMapper<Diagnose>, EntityConverter<DiagnoseDto, Diagnose> {

    @Mapping(target = "medicalRecords", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Override
    Diagnose updateEntity(Diagnose src, @MappingTarget Diagnose dest);
}
