package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.dto.ProtocoleDto;
import projet.creche.model.Protocole;
import projet.creche.repository.ProtocoleRepository;
import projet.creche.service.ProtocoleService;

import java.util.List;
import java.util.Optional;

@Service
public class ProtocoleServiceImpl implements ProtocoleService {

    // Injection de dépendance de ProtocoleRepository
    private final ProtocoleRepository protocoleRepository;

    // Constructeur d'injection de dépendance (recommandé avec Spring)
    @Autowired
    public ProtocoleServiceImpl(ProtocoleRepository protocoleRepository) {
        this.protocoleRepository = protocoleRepository;
    }

    @Override
    public Protocole addProtocole(Protocole protocole) {
        return this.protocoleRepository.save(protocole);
    }

    @Override
    public List<Protocole> getAllProtocoles() {
        return this.protocoleRepository.findAll();
    }

    @Override
    public Protocole findById(Long id) {
        return this.protocoleRepository.findById(id).orElse(null);
    }

    @Override
    public Protocole findByName(String name) {
        return this.protocoleRepository.findByTitre(name);
    }

    @Override
    public void processProtocole(ProtocoleDto protocoleDto) {
        // Utiliser le constructeur avec paramètres pour instancier Protocole
        Protocole protocole = new Protocole(protocoleDto.getTitre(), protocoleDto.getContenu());

        // Enregistrer dans la base de données
        protocoleRepository.save(protocole);
    }

    @Override
    public Protocole updateProtocole(Long id, Protocole newProtocole) {
        Optional<Protocole> existingProtocole = protocoleRepository.findById(id);

        if (existingProtocole.isPresent()) {
            Protocole protocoleToUpdate = existingProtocole.get();
            protocoleToUpdate.setTitre(newProtocole.getTitre());
            protocoleToUpdate.setContenu(newProtocole.getContenu());

            return protocoleRepository.save(protocoleToUpdate); // Sauvegarder le protocole mis à jour
        }
        return null; // Retourne null si l'ID n'est pas trouvé
    }

    @Override
    public boolean deleteProtocole(Long id) {
        if (protocoleRepository.existsById(id)) {
            protocoleRepository.deleteById(id);
            return true; // Retourne true si la suppression a réussi
        }
        return false; // Retourne false si l'ID n'existe pas
    }
}
