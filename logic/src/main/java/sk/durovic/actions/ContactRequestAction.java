package sk.durovic.actions;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import sk.durovic.model.Contact;
import sk.durovic.service.EntityService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ContactRequestAction extends DefaultAction<Contact, Long>{
    public ContactRequestAction(EntityService<Contact, Long, ?> service) {
        super(service);
    }

    @Override
    public Collection<Contact> get(Collection<Contact> entities) {
        if(!entities.iterator().hasNext())
            return Collections.emptyList();

        List<Contact> withId = new ArrayList<>();
        List<Contact> withoutId = new ArrayList<>();

        while(entities.iterator().hasNext()){
            Contact contact = entities.iterator().next();

            if(contact.getId() == null)
                withoutId.add(contact);
            else
                withId.add(contact);
        }

        List<Contact> loadedContacts = new ArrayList<>();

        if(!withId.isEmpty())
            loadedContacts.addAll(super.get(withId));

        if(!withoutId.isEmpty())
            addContactsByPatient(withoutId, loadedContacts);

       return loadedContacts;
    }

    private void addContactsByPatient(List<Contact> contacts, List<Contact> loadedContacts){
        Set<String> patientIds = new HashSet<>();

        List<Contact> filteredContacts = contacts.stream()
                .filter(contact -> hasToBeFiltered(contact, patientIds)).collect(Collectors.toList());

        filteredContacts.forEach(contact -> loadContactsToList(contact, loadedContacts));
    }

    private boolean hasToBeFiltered(Contact contact, Set<String> patientIds){
        String id = contact.getPatient().getId();

        if(patientIds.contains(id)){
            return false;
        }

        patientIds.add(id);
        return true;

    }

    private void loadContactsToList(Contact contact, List<Contact> loadedContacts){
        Example<Contact> example = Example.of(contact);

        loadedContacts.addAll(
                StreamSupport.stream(
                service.findAllByExample(example).spliterator(), false)
                        .collect(Collectors.toList()));
    }
}
