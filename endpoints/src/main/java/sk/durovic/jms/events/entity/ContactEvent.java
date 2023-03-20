package sk.durovic.jms.events.entity;

import sk.durovic.dto.ContactDto;
import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Contact;

public class ContactEvent extends EntityEvent<ContactDto> {

    private void setContact(ContactDto contact){
        this.entity = contact;
    }
}
