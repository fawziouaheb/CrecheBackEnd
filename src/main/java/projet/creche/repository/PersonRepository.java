package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Person;

public interface PersonRepository <T extends Person> extends JpaRepository<T, Long> {
}
