package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
