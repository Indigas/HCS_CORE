package sk.durovic.worker.entity;

import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;
import sk.durovic.model.access.ContactEntity;
import sk.durovic.service.ContactEntityService;
import sk.durovic.worker.EntityWorker;

import java.util.Collection;

public class ContactWorker implements EntityWorker<Contact, Long> {

    private final ContactEntityService service;

    public ContactWorker(EntityServiceManager esm) {
        this.service = (ContactEntityService) esm.getServiceContainer().getService(Contact.class).get();
    }


    @Override
    public Collection<Contact> save(Collection<Contact> entities) {
        return null;
    }

    @Override
    public void delete(Collection<Contact> entities) {

    }

    @Override
    public void deleteById(Long aLong) {
        service.deleteById(aLong);
    }

    @Override
    public void update(Collection<Contact> entity) {

    }

    @Override
    public Collection<Contact> load(Collection<Contact> entity) {
        return null;
    }

    @Override
    public Contact loadById(Long aLong) {
        return service.load(aLong).orElse(null);
    }
}
