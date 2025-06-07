package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.creche.dto.ContactDto;
import projet.creche.service.ContactService;
import projet.creche.tools.MailService;

import java.util.Collections;

@RestController
@RequestMapping("/contact/")
public class ContactRestController {
    private ContactService contactService;
    private final MailService mailService;

    @Autowired
    public ContactRestController(ContactService contactService, MailService mailService) {
        this.contactService = contactService;
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Object> submitForm(@RequestBody ContactDto contactDto) {
        // Traitement du formulaire
        contactService.processContact(contactDto);

        // Envoi de l'email au contact
        mailService.envoyerMailContact(
                contactDto.getEmail(),
                contactDto.getFirstName(),
                contactDto.getLastName(),
                contactDto.getMessage()

        );

        // Réponse JSON
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", "Message envoyé"));
    }

}
