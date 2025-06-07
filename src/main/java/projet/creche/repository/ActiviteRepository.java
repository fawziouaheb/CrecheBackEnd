package projet.creche.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Activite;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    Activite findByName(String name);
}
