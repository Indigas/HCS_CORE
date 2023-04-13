package sk.durovic.helper;

import sk.durovic.model.Contact;
import sk.durovic.util.ContactMerger;
import sk.durovic.util.EntityMerger;

public class EntityMergerHelper {

    public static EntityMerger getMergerForEntity(Class<?> clazz){
        if(clazz.isInstance(Contact.class))
            return new ContactMerger();

        return null;
    }
}
