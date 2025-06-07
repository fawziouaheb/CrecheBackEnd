package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.dto.ActiviteDto;
import projet.creche.model.Activite;
import projet.creche.repository.ActiviteRepository;
import projet.creche.service.ActiviteService;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteServiceImpl implements ActiviteService {

    // Injection de dépendance de ActiviteRepository
    private final ActiviteRepository activiteRepository;

    // Constructeur d'injection de dépendance (recommandé avec Spring)
    @Autowired
    public ActiviteServiceImpl(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    @Override
    public Activite addActivite(Activite activite) {
        return this.activiteRepository.save(activite);
    }

    @Override
    public List<Activite> getAllActivites() {
        return this.activiteRepository.findAll();
    }

    @Override
    public Activite findById(Long id) {
        return this.activiteRepository.findById(id).orElse(null);
    }

    @Override
    public Activite findByName(String name) {
        return this.activiteRepository.findByName(name);
    }

    @Override
    public void processActivite(ActiviteDto activiteDto) {
        // Convertir le DTO en Entité
        Activite activite = new Activite();
        activite.setName(activiteDto.getName());
        activite.setDescription(activiteDto.getDescription());
        activite.setImageUrl(activiteDto.getImageUrl()); // Assuming images is a List<String> representing image URLs

        // Enregistrer dans la base de données
        activiteRepository.save(activite);
    }

    // 🔥 Service pour mettre à jour une activité existante
    @Override
    public Activite updateActivite(Long id, Activite newActivite) {
        Optional<Activite> existingActivite = activiteRepository.findById(id);

        if (existingActivite.isPresent()) {
            Activite activiteToUpdate = existingActivite.get();
            activiteToUpdate.setName(newActivite.getName());
            activiteToUpdate.setDescription(newActivite.getDescription());
            activiteToUpdate.setImageUrl(newActivite.getImageUrl());

            return activiteRepository.save(activiteToUpdate); // Sauvegarder l'activité mise à jour
        }
        return null; // Retourne null si l'ID n'est pas trouvé
    }


    // 🔥 Service pour supprimer une activité
    @Override
    public boolean deleteActivite(Long id) {
        if (activiteRepository.existsById(id)) {
            activiteRepository.deleteById(id);
            return true; // Retourne true si la suppression a réussi
        }
        return false; // Retourne false si l'ID n'existe pas
    }


}
