package sk.durovic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import sk.durovic.dto.ContactDto;
import sk.durovic.model.Contact;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper extends EntityMapper<ContactDto, Contact>, EntityConverter<ContactDto, Contact> {

    @Override
    @Mapping(target = "patient", ignore = true)
    Contact updateEntity(ContactDto dto, @MappingTarget Contact contact);

    @Mapping(target = "patientId", source = "entity.patient.id")
    @Override
    ContactDto convert2Dto(Contact entity);

    @Override
    @Mapping(target = "patient", expression = "java(sk.durovic.helper.EntityReferenceCreator.convertIdToEntity(dto.getPatientId(), Patient.class))")
    Contact convert2Entity(ContactDto dto);

}
