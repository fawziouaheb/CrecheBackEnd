package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.dto.ContactDto;
import projet.creche.model.Contact;
import projet.creche.repository.ContactRepository;
import projet.creche.service.ContactService;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    // Injection de dépendance de ContactRepository
    private final ContactRepository contactRepository;

    // Constructeur d'injection de dépendance (recommandé avec Spring)
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact addContact(Contact contact) {
        return this.contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public Contact findByEmail(String email) {
        return this.contactRepository.findByEmail(email);
    }

    @Override
    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElse(null);
    }

    public void processContact(ContactDto contactDto) {
        // Convertir le DTO en Entité
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmail(contactDto.getEmail());
        contact.setMobile(contactDto.getMobile());
        contact.setMessage(contactDto.getMessage());

        // Enregistrer dans la base de données
        contactRepository.save(contact);
    }
}
