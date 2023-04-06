package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.model.Contact;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.ContactEntityService;

@RestController
@RequestMapping("/contact")
public class ContactController extends EntityController<Contact, Long> {

    public ContactController(ContactEntityService service) {
        super(new RestRequestProcessor<>(service));
    }
}
