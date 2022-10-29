package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.ContactRepository;
import sk.durovic.service.ContactEntityService;

@Service
public class ContactEntityServiceImpl extends ContactEntityService {

    @Autowired
    public ContactEntityServiceImpl(ContactRepository repository) {
        this.repo = repository;
    }


}
