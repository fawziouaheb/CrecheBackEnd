package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.WorkingSession;

import java.sql.Date;
import java.util.List;

public interface WorkingSessionRepository  extends JpaRepository<WorkingSession, Long> {
    List<WorkingSession> findByEmployeIdAndDateSessionBetween(Long employeId, Date dateDebut, Date dateFin);
    List<WorkingSession> findByEmployeId(Long employeId);
}
