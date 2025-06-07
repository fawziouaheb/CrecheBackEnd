package projet.creche.service;

import projet.creche.model.Rapport;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface RapportService {
    Rapport getRapport(Long id);
    Rapport saveRapport(Rapport rapport);
    Optional<Rapport> findByStructureIdAndDate(Long structureId, Date date);
}
