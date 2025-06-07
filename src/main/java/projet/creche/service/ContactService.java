package projet.creche.service;

import org.springframework.stereotype.Service;
import projet.creche.dto.ContactDto;
import projet.creche.model.Candidate;
import projet.creche.model.Contact;

import java.util.List;
@Service

public interface ContactService {
    public Contact addContact(Contact contact);
    List<Contact> getAll();
    Contact findByEmail(String email);
    Contact findById(Long id);
    public void processContact(ContactDto contactDto) ;
}
