package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.DiseaseDto;
import sk.durovic.model.Disease;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiseaseMapper extends EntityMapper<Disease>, EntityConverter<DiseaseDto, Disease> {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Override
    Disease updateEntity(Disease src, @MappingTarget Disease dest);

    @Mapping(target = "patientId", source = "entity.patient.id")
    @Override
    DiseaseDto convert2Dto(Disease entity);

    @Mapping(target = "patient", expression = "java(sk.durovic.helper.EntityReferenceCreator.convertIdToEntity(dto.getPatientId(), Patient.class))")
    @Override
    Disease convert2Entity(DiseaseDto dto);
}
