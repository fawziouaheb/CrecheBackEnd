package projet.creche.service;

import org.springframework.stereotype.Service;
import projet.creche.dto.ProtocoleDto;
import projet.creche.model.Protocole;

import java.util.List;

@Service
public interface ProtocoleService {

    // Méthode pour ajouter un nouveau protocole
    Protocole addProtocole(Protocole protocole);

    // Méthode pour récupérer tous les protocoles
    List<Protocole> getAllProtocoles();

    // Méthode pour récupérer un protocole par son ID
    Protocole findById(Long id);

    // Méthode pour récupérer un protocole par son nom
    Protocole findByName(String name);

    // Méthode pour traiter un ProtocoleDto (enregistrer dans la base de données)
    void processProtocole(ProtocoleDto protocoleDto);

    // Méthode pour mettre à jour un protocole
    Protocole updateProtocole(Long id, Protocole protocole);

    // Méthode pour supprimer un protocole
    boolean deleteProtocole(Long id);
}
