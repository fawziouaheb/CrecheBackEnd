package projet.creche.repository.inscription.preinscription;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Candidate;
import projet.creche.model.inscription.PreInscription;

import java.util.List;

public interface PreInscriptionRerository extends JpaRepository<PreInscription, Long> {
        List<PreInscription> findByStructure_StructureName(String structureName);
}
