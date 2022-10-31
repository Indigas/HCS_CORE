package sk.durovic.jms.messaging.event.entity;

import sk.durovic.jms.messaging.event.Event;
import sk.durovic.model.Contact;

public class ContactEvent extends Event<Contact> {

    private void setContact(Contact contact){
        this.entity = contact;
    }
}
