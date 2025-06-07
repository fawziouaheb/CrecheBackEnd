package projet.creche.resetAPI;

import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.customizers.SpecPropertiesCustomizer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projet.creche.dto.StructureDto;
import projet.creche.mapper.StructureMapper;
import projet.creche.model.City;
import projet.creche.model.Person;
import projet.creche.model.Structure;
import projet.creche.repository.StructureRepository;
import projet.creche.service.CityService;
import projet.creche.service.StructureService;
import projet.creche.tools.ApiResponse;
import org.springframework.http.MediaType;
import projet.creche.configs.PathFile;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * Cette classe permet de définir les routes pour la gestion des structures.
 *
 * @author Fawzi Ouaheb
 */
@RestController
@RequestMapping("/administrator/structure")
public class StructureRestController {
    private final StructureRepository structureRepository;
    private final StructureService structureService;
    private final CityService cityService;

    @Autowired
    public StructureRestController(StructureService structureService, CityService cityService, StructureRepository structureRepository) {
        this.structureService = structureService;
        this.cityService = cityService;
        this.structureRepository = structureRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createStructure(@RequestBody StructureDto structureDto) {
        City city = this.cityService.findById(structureDto.getCityId());
        Structure structure = StructureMapper.mappeDtoToEntity(structureDto);
        structure.setCity(city);
        structure.setAvantages(structureDto.getAvantages());
        structure.setDescription(structureDto.getDescription());
        structure.setImages(structureDto.getImages());

        this.structureService.persistStructure(structure);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, structureDto, "Structure créée avec succès"));
    }

    @PostMapping("/get/{name}")
    public ResponseEntity<Object> getStructure(
            @Parameter(description = "Le nom de la structure à charger", required = true)
            @PathVariable("name") String name) {

        Structure structureFound = this.structureService.findByStructureName(name).get(0);
        StructureDto structureDto = StructureMapper.mappeEntityToDto(structureFound);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, structureDto, "Structure bien chargée"));
    }


    /**
     * Cette méthode permet de modifier une structure dans le système
     */
    @PostMapping("/edit")
    public ResponseEntity<Object> updateStructure(@RequestBody StructureDto structureDto) {
        System.out.println("Modification de la structure en cours...");
        Structure structure = this.structureService.findById(structureDto.getId());
        if (structure == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Structure non trouvée"));
        }

        City city = this.cityService.findById(structureDto.getCityId());
        structure.setCity(city);
        structure.setStructureName(structureDto.getStructureName());
        structure.setAdresse(structureDto.getAdresse());
        structure.setCapacity(structureDto.getCapacity());
        structure.setStatut(structureDto.getStatut());
        structure.setMobile(structureDto.getMobile());
        structure.setLog(structureDto.getLog());
        structure.setLat(structureDto.getLat());
        structure.setAvantages(structureDto.getAvantages());
        structure.setDescription(structureDto.getDescription());
        structure.setImages(structureDto.getImages());

        this.structureService.update(structure);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, structureDto, "Structure mise à jour avec succès"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<StructureDto>> getAllStructures() {
        List<Structure> structures = structureService.getAllStructure();

        if (structures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retourne un code 204 si aucune structure n'est trouvée
        }

        // Ajoute l'URL complète pour chaque image si nécessaire
        structures.forEach(structure -> {
            List<String> updatedImages = structure.getImages().stream()
                    .map(image -> {
                        if (image != null && !image.startsWith("http")) {
                            return "http://localhost:8080/images/" + image; // Construit l'URL complète de l'image
                        }
                        return image;
                    })
                    .toList();
            structure.setImages(updatedImages);
        });

        // Mapper les entités en DTOs
        List<StructureDto> structureDtos = StructureMapper.mapperEntityListToDtoList(structures);

        return new ResponseEntity<>(structureDtos, HttpStatus.OK); // Retourne les structures avec un code 200
    }

    /**
     * récupere la liste des noms de structures
     */
    @GetMapping("/getAllNames")
    public ResponseEntity<Object> getAllStructuresNames() {
        List<String> structureNames = structureService.getAllStructureNames();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, structureNames, "Les noms de structures sont bien chargée"));
    }
    /**
     * Charger les structures de la ville d'abbeville
     */
    @GetMapping("/getStructureAbbeville")
    public ResponseEntity<List<StructureDto>> getStructureAbbeville() {
        List<StructureDto> structures = StructureMapper.mapperEntityListToDtoList(this.structureService.getStructureAbbeville(3L));
        return new ResponseEntity<>(structures, HttpStatus.OK);
    }

    /**
     * Supprimer une ville par son ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStructure(
            @Parameter(description = "L'ID de structure à charger", required = true)
            @PathVariable Long id) {
        // Recherche de la structure
        structureService.deleteStructureById(id);


        // Retourne une réponse de succès
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Structure supprimée avec succès"));
    }



    @Autowired
    private EntityManager entityManager;



    @PostMapping(value = "/uploadImage/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImages(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        System.out.println("ID reçu : " + id);  // <-- Log pour debug

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Aucun fichier sélectionné."));
        }

        try {
            String baseUploadDir = System.getProperty("user.dir") + "/" + PathFile.STRUCTURE.getDescription();
            String uploadDir = baseUploadDir + id + "/";
            System.out.println("Chemin d'upload : " + uploadDir);  // <-- Log pour debug

            File directory = new File(uploadDir);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                System.out.println("Dossier créé : " + created);
            }

            String sanitizedFileName = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            File uploadFile = new File(uploadDir + sanitizedFileName);

            file.transferTo(uploadFile);
            System.out.println("Image téléchargée : " + uploadFile.getAbsolutePath());

            String imageUrl = "http://localhost:8080/images/structure/" + id + "/" + sanitizedFileName;

            return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de l'upload de l'image."));
        }
    }



    @GetMapping("/images/{imageName}")
    public ResponseEntity<byte[]> getImages(@PathVariable String imageName) {
        String imageDirectory = "src/main/resources/static/images/";
        Path imagePath = Paths.get(imageDirectory + imageName);

        System.out.println("Requête pour obtenir l'image : " + imageName);
        System.out.println("Chemin complet de l'image : " + imagePath.toString());

        try {
            // Vérification si le fichier existe
            if (!Files.exists(imagePath)) {
                System.out.println("Fichier introuvable : " + imagePath.toString());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Vérification des permissions
            if (!Files.isReadable(imagePath)) {
                System.out.println("Fichier non lisible (permissions insuffisantes) : " + imagePath.toString());
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // Lecture du fichier
            byte[] imageBytes = Files.readAllBytes(imagePath);
            System.out.println("Fichier trouvé et lu avec succès : " + imagePath.toString());

            // Détection du type MIME
            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
                System.out.println("Type MIME non détecté, valeur par défaut utilisée : " + contentType);
            } else {
                System.out.println("Type MIME détecté : " + contentType);
            }

            return ResponseEntity.ok()
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getImagesByStructure/{id}")
    public ResponseEntity<List<String>> getImagesByStructure(@PathVariable Long id) {
        String imageDirectory = "src/main/resources/static/images/";
        File directory = new File(imageDirectory);

        // Vérifie si le répertoire existe
        if (!directory.exists() || !directory.isDirectory()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of("Le répertoire d'images n'existe pas"));
        }

        // Parcours les fichiers du répertoire en filtrant par l'ID de la structure
        File[] files = directory.listFiles((dir, name) -> name.startsWith(id + "_"));
        if (files == null || files.length == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of("Aucune image trouvée pour cette structure"));
        }

        // Crée la liste des URLs des images
        List<String> imageUrls = new ArrayList<>();
        for (File file : files) {
            String imageUrl = "http://localhost:8080/images/" + file.getName();
            imageUrls.add(imageUrl);
        }

        // Retourne les URLs des images trouvées avec un code 200
        return ResponseEntity.ok(imageUrls);
    }


    @GetMapping("/getStructureById/{id}")
    public ResponseEntity<Object> getStructureById(@PathVariable Long id) {
        // Recherche de la structure par ID
        Structure structure = this.structureService.findById(id);

        // Vérification si la structure existe
        if (structure == null) {
            // Si non trouvée, retourne un ApiResponse avec un message d'erreur
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Structure non trouvée"));
        }

        // Mapper la structure à un DTO
        StructureDto structureDto = StructureMapper.mappeEntityToDto(structure);

        // Ajouter les images avec l'URL complète
        List<String> updatedImages = structure.getImages().stream()
                .map(image -> {
                    if (image != null && !image.startsWith("http")) {
                        return "http://localhost:8080/images/" + image; // URL complète
                    }
                    return image;
                }).toList();
        structureDto.setImages(updatedImages); // Met à jour les images du DTO

        // Retourne directement un ResponseEntity<StructureDto>
        return ResponseEntity.ok(structureDto);
    }


}