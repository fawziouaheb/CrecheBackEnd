package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.Rapport;
import projet.creche.repository.RapportRepository;
import projet.creche.service.RapportService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class RapportServiceImpl implements RapportService {
    private RapportRepository rapportRepository;

    @Autowired
    public RapportServiceImpl(RapportRepository rapportRepository) {
        this.rapportRepository = rapportRepository;
    }

    @Override
    public Rapport getRapport(Long id) {
        return this.rapportRepository.findById(id).get();
    }

    @Override
    public Rapport saveRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    @Override
    public Optional<Rapport> findByStructureIdAndDate(Long structureId, Date date) {
        return this.rapportRepository.findByStructureIdAndRapportDate(structureId, date);
    }

}
