package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Contact;

import java.util.Collection;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
