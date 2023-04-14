package sk.durovic.mapper;

import org.mapstruct.*;
import sk.durovic.dto.ContactDto;
import sk.durovic.events.Event;
import sk.durovic.model.Contact;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper extends EntityMapper<ContactDto, Contact>, EntityConverter2Dto<ContactDto, Contact> {

    @Override
    @Mapping(target = "patient", ignore = true)
    Contact updateEntity(ContactDto dto, @MappingTarget Contact contact);
}
