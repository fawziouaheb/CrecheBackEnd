package projet.creche.resetAPI;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projet.creche.model.Activite;
import projet.creche.service.ActiviteService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activites/")
public class ActiviteRestController {

    private ActiviteService activiteService;

    @Autowired
    public ActiviteRestController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    // GET pour récupérer toutes les activités
    @GetMapping
    public ResponseEntity<List<Activite>> getAllActivites() {
        List<Activite> activites = activiteService.getAllActivites();
        if (activites.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retourne un code 204 si aucune activité n'est trouvée
        }

        // Ajoute l'URL complète de l'image si nécessaire
        activites.forEach(activite -> {
            String imageUrl = activite.getImageUrl();
            if (imageUrl != null && !imageUrl.startsWith("http")) {
                activite.setImageUrl("http://localhost:8080/activites/images/" + imageUrl);  // Construit l'URL complète de l'image
            }
        });

        return new ResponseEntity<>(activites, HttpStatus.OK); // Retourne les activités avec un code 200
    }

    // GET pour récupérer une activité par ID
    @GetMapping("/{id}")
    public ResponseEntity<Activite> getActiviteById(
            @Parameter(description = "ID de l'activité à charger", required = true)
            @PathVariable Long id) {
        Activite activite = activiteService.findById(id);
        if (activite == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si l'activité n'est pas trouvée
        }

        String imageUrl = activite.getImageUrl();
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            activite.setImageUrl("http://localhost:8080/activites/images/" + imageUrl);  // Construit l'URL complète de l'image
        }

        return new ResponseEntity<>(activite, HttpStatus.OK); // Retourne l'activité avec un code 200
    }

    // GET pour récupérer une activité par nom
    @GetMapping("/name/{name}")
    public ResponseEntity<Activite> getActiviteByName(
            @Parameter(description = "Le nom de l'activité à charger", required = true)
            @PathVariable("name") String name) {
        Activite activite = activiteService.findByName(name);
        if (activite == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si l'activité n'est pas trouvée
        }

        String imageUrl = activite.getImageUrl();
        if (imageUrl != null && !imageUrl.startsWith("http")) {
            activite.setImageUrl("http://localhost:8080/activites/images/" + imageUrl);  // Construit l'URL complète de l'image
        }

        return new ResponseEntity<>(activite, HttpStatus.OK); // Retourne l'activité avec un code 200
    }

    // POST pour ajouter une nouvelle activité
    @PostMapping
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) {
        // Logique pour créer une nouvelle activité
        Activite newActivite = activiteService.addActivite(activite); // Appelle le service pour ajouter l'activité
        return new ResponseEntity<>(newActivite, HttpStatus.CREATED); // Retourne la nouvelle activité avec un code 201
    }

    // GET pour récupérer l'image d'une activité
    @GetMapping("/images/{imageName}")
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "Charger l'image d'activité par nom d'image", required = true)
            @PathVariable String imageName) {
        // Répertoire où les images sont stockées
        String imageDirectory = "src/main/resources/static/images/";

        try {
            // Créer le chemin de l'image
            Path imagePath = Paths.get(imageDirectory + imageName);
            // Lire le fichier image en tant que tableau de bytes
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Retourner l'image avec le type MIME approprié
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Assurez-vous de remplacer par le bon type MIME si l'image est en PNG, etc.
                    .body(imageBytes);
        } catch (IOException e) {
            // Si l'image n'est pas trouvée, retour d'une erreur 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT pour modifier une activité
    @PutMapping("/{id}")
    public ResponseEntity<Activite> updateActivite(
            @Parameter(description = "ID de l'activité à mettre à jour", required = true)
            @PathVariable("id") Long id,
            @RequestBody Activite activite) {

        Activite updatedActivite = activiteService.updateActivite(id, activite);

        if (updatedActivite == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedActivite, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivite(@PathVariable Long id) {
        activiteService.deleteActivite(id);
    }


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Aucun fichier sélectionné."));
        }

        try {
            String uploadDir = new File("src/main/resources/static/images/").getAbsolutePath() + "/";
            File directory = new File(uploadDir);

            if (!directory.exists() && !directory.mkdirs()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Impossible de créer le dossier de destination."));
            }

            String sanitizedFileName = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            String fileName = System.currentTimeMillis() + "_" + sanitizedFileName;
            File uploadFile = new File(uploadDir + fileName);

            file.transferTo(uploadFile);

            // Retourner l'URL de l'image dans un objet JSON
            return ResponseEntity.ok(Map.of("imageUrl", "http://localhost:8080/activites/images/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erreur lors de l'upload de l'image."));
        }
    }







}
