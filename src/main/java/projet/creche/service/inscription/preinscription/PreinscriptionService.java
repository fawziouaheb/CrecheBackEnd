package projet.creche.service.inscription.preinscription;

import projet.creche.dto.inscription.preinscription.PreinscriptionDto;
import projet.creche.model.Structure;
import projet.creche.model.inscription.PreInscription;

import java.util.List;

public interface PreinscriptionService {
    PreInscription savePreInscription(PreInscription preInscription);
    boolean deletePreInscriptionById(Long id);
    List<PreInscription> getAll();
    PreInscription getPreInscriptionById(Long id);
    String getMailById(Long id);
    List<PreInscription> getPreinscriptionByStructureName(String structureName);
}
