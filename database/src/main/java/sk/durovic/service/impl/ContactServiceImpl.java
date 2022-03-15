package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.model.Contact;
import sk.durovic.repository.ContactRepository;
import sk.durovic.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repo;

    @Override
    public Contact save(Contact object) {
        return null;
    }

    @Override
    public void delete(Contact object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
