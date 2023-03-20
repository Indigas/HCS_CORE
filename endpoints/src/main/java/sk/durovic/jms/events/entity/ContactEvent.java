package sk.durovic.jms.events.entity;

import sk.durovic.jms.messaging.event.EntityEvent;
import sk.durovic.model.Contact;

public class ContactEvent extends EntityEvent<Contact> {

    private void setContact(Contact contact){
        this.entity = contact;
    }
}
