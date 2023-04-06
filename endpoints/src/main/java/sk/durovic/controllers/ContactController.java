package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.model.Contact;

@RestController
@RequestMapping("/contact")
public class ContactController extends EntityController<Contact, Long> {


}
