package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.DiseaseDto;
import sk.durovic.model.Disease;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiseaseMapper extends EntityMapper<DiseaseDto, Disease>, EntityConverter2Dto<DiseaseDto, Disease>{

    @Mapping(target = "patient", ignore = true)
    @Override
    Disease updateEntity(DiseaseDto src, @MappingTarget Disease dest);
}
