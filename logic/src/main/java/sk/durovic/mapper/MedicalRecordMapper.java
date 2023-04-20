package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.model.MedicalRecord;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicalRecordMapper extends EntityMapper<MedicalRecord>, EntityConverter<MedicalRecordDto, MedicalRecord> {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "diagnose", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Override
    MedicalRecord updateEntity(MedicalRecord src, @MappingTarget MedicalRecord dest);

    @Mapping(target = "patientId", source = "entity.patient.id")
    @Mapping(target = "diagnoseId", source = "entity.diagnose.id")
    @Override
    MedicalRecordDto convert2Dto(MedicalRecord entity);

    @Mapping(target = "patient", expression = "java(sk.durovic.helper.EntityReferenceCreator.convertIdToEntity(dto.getPatientId(), Patient.class))")
    @Mapping(target = "diagnose", expression = "java(sk.durovic.helper.EntityReferenceCreator.convertIdToEntity(dto.getDiagnoseId(), Diagnose.class))")
    @Override
    MedicalRecord convert2Entity(MedicalRecordDto dto);
}
