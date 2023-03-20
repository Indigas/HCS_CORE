package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.dto.ContactDto;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Contact;
import sk.durovic.worker.EntityWorkerFactory;

@RestController
@RequestMapping("/contact")
public class ContactController extends EntityController<ContactDto, Contact, Long> {


    public ContactController(EntityManagerCreator creator) {
        super(EntityWorkerFactory.createEntityWorker(creator));
    }
}
