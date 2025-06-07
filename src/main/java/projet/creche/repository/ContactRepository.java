package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmail(String email);
}
