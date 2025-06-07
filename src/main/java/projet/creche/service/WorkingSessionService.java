package projet.creche.service;

import lombok.Data;
import projet.creche.model.WorkingSession;

import java.sql.Date;
import java.util.List;

public interface WorkingSessionService {
    WorkingSession save(WorkingSession workingSession);
    List<WorkingSession> findSessionsBetweenDates(Long employeId, Date dateDebut, Date dateFin);
    List<WorkingSession> getAllSessionsByEmploye(Long employeId);
}
