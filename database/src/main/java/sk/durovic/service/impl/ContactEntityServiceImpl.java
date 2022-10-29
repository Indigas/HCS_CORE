package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.ContactRepository;
import sk.durovic.service.ContactService;

@Service
public class ContactServiceImpl extends ContactService {

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repo = repository;
    }


}
