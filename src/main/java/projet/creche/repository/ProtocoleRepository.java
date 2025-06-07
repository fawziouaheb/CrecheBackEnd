package projet.creche.repository;

import projet.creche.model.Protocole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtocoleRepository extends JpaRepository<Protocole, Long> {
    Protocole findByTitre(String name);
}