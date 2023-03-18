package sk.durovic.worker.entity;

import sk.durovic.manager.EntityManager;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Contact;
import sk.durovic.service.ContactEntityService;
import sk.durovic.worker.EntityWorker;

import java.io.IOException;
import java.util.Collection;

public class ContactWorker implements EntityWorker<Contact, Long> {

    //private final ContactEntityService service;
    private final EntityManagerCreator creator;

    public ContactWorker(EntityManagerCreator creator) {
        //this.service = (ContactEntityService) esm.getServiceContainer().getService(Contact.class).get();
        this.creator = creator;
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
        try(EntityManager em = creator.getBasicEntityManager()){
            em.remove(em.getReference(Contact.class, aLong));

            em.commit();
        } catch (IOException e){

        }
        //service.deleteById(aLong);
    }

    @Override
    public void update(Collection<Contact> entity) {

    }

    @Override
    public void updateEntity(Long aLong, Contact entity) {

    }

    @Override
    public Collection<Contact> load(Collection<Contact> entity) {
        return null;
    }

    @Override
    public Contact loadById(Long aLong) {
        return creator.getBasicEntityManager().load(Contact.class, aLong).orElse(null);
        //return service.load(aLong).orElse(null);
    }
}
