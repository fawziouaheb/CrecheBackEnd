package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.City;
import projet.creche.model.Structure;

import java.util.List;

/**
 * cette méthode permet de charger les méthode pour les requqte sql depuis le JpaRepository
 * @author Fawzi Ouaheb
 */
public interface StructureRepository extends JpaRepository<Structure, Long> {
    boolean existsByStructureName( String StructureName);
    List<Structure> findByStructureName(String StructureName);
    List<Structure> findByCityId(Long cityId);

    List<Structure> findByCity(City city);
}
