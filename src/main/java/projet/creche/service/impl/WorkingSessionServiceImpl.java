package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.WorkingSession;
import projet.creche.repository.WorkingSessionRepository;
import projet.creche.service.WorkingSessionService;

import java.sql.Date;
import java.util.List;


@Service
public class WorkingSessionServiceImpl implements WorkingSessionService {
    private  final WorkingSessionRepository workingSessionRepository;

    @Autowired
    public WorkingSessionServiceImpl(WorkingSessionRepository workingSessionRepository) {
        this.workingSessionRepository = workingSessionRepository;
    }

    @Override
    public WorkingSession save(WorkingSession workingSession) {
        return this.workingSessionRepository.save(workingSession);
    }

    @Override
    public List<WorkingSession> findSessionsBetweenDates(Long employeId, Date dateDebut, Date dateFin) {
        return this.workingSessionRepository.findByEmployeIdAndDateSessionBetween(employeId, dateDebut, dateFin);
    }

    @Override
    public List<WorkingSession> getAllSessionsByEmploye(Long employeId) {
        return this.workingSessionRepository.findByEmployeId(employeId);
    }
}
