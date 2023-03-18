package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;
import sk.durovic.worker.entity.ContactWorker;

@RestController
@RequestMapping("/contact")
public class ContactController extends EntityController<Contact, Long> {


    public ContactController(EntityManagerCreator creator) {
        super(new ContactWorker(creator));
    }
}
