package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Rapport;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    Optional<Rapport> findByStructureIdAndRapportDate(Long structureId, Date dateCreated);


}
