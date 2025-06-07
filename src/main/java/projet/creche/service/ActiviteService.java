package projet.creche.service;

import org.springframework.stereotype.Service;
import projet.creche.dto.ActiviteDto;
import projet.creche.model.Activite;

import java.util.List;

@Service
public interface ActiviteService {

    // Méthode pour ajouter une nouvelle activité
    Activite addActivite(Activite activite);

    // Méthode pour récupérer toutes les activités
    List<Activite> getAllActivites();

    // Méthode pour récupérer une activité par son ID
    Activite findById(Long id);

    // Méthode pour récupérer une activité par son nom
    Activite findByName(String name);

    // Méthode pour traiter une ActiviteDto (enregistrer dans la base de données)
    void processActivite(ActiviteDto activiteDto);

    Activite updateActivite(Long id, Activite activite);

    boolean deleteActivite(Long id);

}
