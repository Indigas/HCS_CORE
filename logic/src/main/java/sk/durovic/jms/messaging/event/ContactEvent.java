package sk.durovic.jms.messaging.event;

import sk.durovic.model.Contact;

public class ContactEvent extends Event<Contact>{

    private void setContact(Contact contact){
        this.entity = contact;
    }
}
