package projet.creche.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet.creche.model.Structure;
import projet.creche.repository.CityRepository;
import projet.creche.repository.StructureRepository;
import projet.creche.service.CityService;
import projet.creche.service.StructureService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * cette class implement les methodes du service concernant la structure
 * @author Fawzi Ouaheb
 */
@Service
public class StructureServiceImpl implements StructureService {

    private static final Logger logger = LoggerFactory.getLogger(StructureService.class);
private CityService cityService;
    @Autowired
    private StructureRepository structureRepository;

    private CityRepository cityRepository;
    @Override
    public List<Structure> findByStructureName(String structureName) {
        return this.structureRepository.findByStructureName(structureName);
    }

    @Override
    public List<Structure> getStructureAbbeville(Long idCity) {
        return this.structureRepository.findByCityId(idCity);
    }

    @Override
    public List<String> getAllStructureNames() {
        return structureRepository.findAll().stream()
                .map(Structure::getStructureName)
                .collect(Collectors.toList());
    }



    @Autowired
    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    @Override
    public Structure updateStructure(Structure structure) {
        if (!isValidAndFilter(structure))
            throw new EntityExistsException("Ce role existe déjà ou vérifiez les données saisies");
        return this.structureRepository.saveAndFlush(structure);
    }

    @Override
    public Structure persistStructure(Structure structure) {
        // Check if a structure with the same name exists
        if (this.structureRepository.existsByStructureName(structure.getStructureName())) {
            throw new EntityExistsException("A structure with the name '" + structure.getStructureName() + "' already exists.");
        }

        // Save the structure if validation passes
        try {
            return this.structureRepository.save(structure);
        } catch (Exception e) {
            logger.error("Error saving structure: ", e);
            throw new RuntimeException("An error occurred while saving the structure", e);
        }
    }



    @Autowired
    private EntityManager entityManager;
    @Override
    public Structure findById(Long id) {
        return this.structureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Structure> getAllStructure() {
        return this.structureRepository.findAll();
    }

    @Override
    public Structure update(Structure structure) {
        return structureRepository.save(structure);
    }

    @Transactional
    public void deleteStructureById(Long id) {
        // Recherche de la structure dans la base de données
        Structure structure = structureRepository.findById(id).orElse(null);

        if (structure != null) {
            structure.setCity(null);
            entityManager.flush(); // envoie update (city = null)
            entityManager.clear(); // détache l'entité du contexte
            structure = entityManager.find(Structure.class, id); // recharge
            entityManager.remove(structure); // suppression
        }
    }







    // Ajouter la méthode de conversion ici


    /**
     * cette méthode vérifie la validité des données du model
     * @param structure la structure à ajouter dans la base des données
     * @return la structure créée
     */
    public boolean isValidAndFilter(Structure structure) {
        if (structure == null) {
            throw new IllegalArgumentException("Structure is null.");
        }

        // Vérification des champs
        if (isNullOrEmpty(structure.getStructureName())) {
            throw new IllegalArgumentException("Structure name is missing or empty.");
        }
        if (isNullOrEmpty(structure.getAdresse())) {
            throw new IllegalArgumentException("Adresse is missing or empty.");
        }
        if (isNullOrEmpty(structure.getMobile())) {
            throw new IllegalArgumentException("Mobile number is missing or empty.");
        }
        if (structure.getMobile().length() != 10) {
            throw new IllegalArgumentException("Mobile number must have 10 digits.");
        }
        if (isNullOrEmpty(structure.getStatut())) {
            throw new IllegalArgumentException("Statut is missing or empty.");
        }
        if (isNullOrEmpty(structure.getAvantages())) {
            throw new IllegalArgumentException("Avantages is missing or empty.");
        }
        if (isNullOrEmpty(structure.getDescription())) {
            throw new IllegalArgumentException("Description is missing or empty.");
        }
        if (structure.getImages() == null || structure.getImages().isEmpty()) {
            throw new IllegalArgumentException("Images are missing or empty.");
        }
        if (structure.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }

        // Clean and format data
        structure.setStructureName(structure.getStructureName().trim().toUpperCase());
        structure.setAdresse(structure.getAdresse().trim());
        structure.setMobile(structure.getMobile().trim());
        structure.setStatut(structure.getStatut().trim().toUpperCase());
        structure.setAvantages(structure.getAvantages().trim());
        return true;
    }

    // Utility method for null or empty checks
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }


}