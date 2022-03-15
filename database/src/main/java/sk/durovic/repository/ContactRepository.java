package sk.durovic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Contact;


public interface ContactRepository extends CrudRepository<Contact, Long> {
}
