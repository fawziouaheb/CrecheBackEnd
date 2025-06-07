package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Structure;

import java.util.List;

public interface StructureImageRepository extends JpaRepository<Structure, Long> {
}
