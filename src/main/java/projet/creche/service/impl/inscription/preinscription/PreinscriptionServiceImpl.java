package projet.creche.service.impl.inscription.preinscription;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.model.inscription.PreInscription;
import projet.creche.repository.inscription.preinscription.PreInscriptionRerository;
import projet.creche.service.inscription.preinscription.PreinscriptionService;

import java.util.List;

@Service
public class PreinscriptionServiceImpl implements PreinscriptionService {
    private PreInscriptionRerository preInscriptionRerository;

    @Autowired
    public PreinscriptionServiceImpl(PreInscriptionRerository preInscriptionRerository) {
        this.preInscriptionRerository = preInscriptionRerository;
    }

    @Override
    public PreInscription savePreInscription(PreInscription preInscription) {
        return preInscriptionRerository.save(preInscription);
    }

    @Override
    public boolean deletePreInscriptionById(Long id) {
        if (preInscriptionRerository.existsById(id)) {
            preInscriptionRerository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PreInscription> getAll() {
        return preInscriptionRerository.findAll();
    }

    @Override
    public PreInscription getPreInscriptionById(Long id) {
        return preInscriptionRerository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pré-inscription avec l'ID " + id + " non trouvée"));
    }
    @Override
    public String getMailById(Long id) {
        PreInscription preInscription = preInscriptionRerository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pré-inscription avec l'ID " + id + " non trouvée"));

        // Récupérer l'email du premier parent (si disponible)
        String email = preInscription.getParents().isEmpty()
                ? null
                : preInscription.getParents().get(0).getEmail();

        if (email == null || email.isBlank()) {
            throw new IllegalStateException("Aucun email trouvé pour la pré-inscription avec l'ID " + id);
        }

        return email;
    }

    /**
     * @param structureName
     * @return Liste de preinscription qui appartient à la structure choisi
     */
    @Override
    public List<PreInscription> getPreinscriptionByStructureName(String structureName) {
        return preInscriptionRerository.findByStructure_StructureName(structureName);
    }

}
