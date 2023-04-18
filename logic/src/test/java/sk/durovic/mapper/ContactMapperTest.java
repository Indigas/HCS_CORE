package sk.durovic.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sk.durovic.dto.ContactDto;
import sk.durovic.model.Contact;

class ContactMapperTest {

    private Contact contact;
    private ContactMapper mapper;

    @BeforeEach
    void setUp(){
        contact = new Contact();
        contact.setFullName("Marek");

        mapper = Mappers.getMapper(ContactMapper.class);
    }

    @Test
    void updateEntity() {
        ContactDto dto = mapper.convert2Dto(contact);
        System.out.println(dto);
    }

    @Test
    void convert2Dto() {
    }

    @Test
    void convert2Entity() {
    }
}