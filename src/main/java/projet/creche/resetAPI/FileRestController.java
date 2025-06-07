package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.configs.Links;
import projet.creche.configs.PathFile;
import projet.creche.model.Employe;
import projet.creche.model.File;
import projet.creche.model.Parent;
import projet.creche.model.Person;
import projet.creche.service.FileService;
import projet.creche.service.PersonService;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.FileUploadService;
import projet.creche.tools.MailService;
import projet.creche.utilitaires.PdfUploadRequest;


import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Director/files")
public class FileRestController {

    private FileService fileService;
    private PersonService personService;
    private MailService mailService;

    @Autowired
    public FileRestController(FileService fileService,PersonService personService,MailService mailService) {
        this.fileService = fileService;
        this.personService = personService;
        this.mailService = mailService;
    }

    @PostMapping("/upload-pdf")
    public ResponseEntity<ApiResponse<Object>> uploadPdf(@RequestBody PdfUploadRequest request) {
        // Recherche de l'employé ou du parent
        Employe employe = personService.findEmployeById(request.getIdPackage());
        Parent parent = personService.findParentById(request.getIdPackage());

        if (employe == null && parent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Aucun parent ou employé trouvé avec l'ID fourni."));
        }

        // Initialisation des variables
        String path;
        String destinataireEmail;
        String lienDossier;
        File file = new File();

        Person person;
        if (employe != null) {
            path = PathFile.REPOSITIRY_EMPLOYEE.getDescription(); // Correction ici
            destinataireEmail = employe.getCompte().getUsername();
            lienDossier = Links.LINKREPOSITORYEMPLOYEE.getDescription() + employe.getId();
            person = employe;
        } else {
            path = PathFile.REPOSITORY_PARENT.getDescription();
            destinataireEmail = parent.getCompte().getUsername();
            lienDossier = Links.LINKREPOSITORYPARENT.getDescription() + parent.getId();
            person = parent;
        }

        // Upload du fichier
        Map<String, Object> result = FileUploadService.uploadPdf(request, path);
        boolean success = (boolean) result.getOrDefault("success", false);

        if (!success) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Échec de l'upload du fichier."));
        }

        // Création et sauvegarde du fichier
        file.setFileName(request.getFileName());
        file.setFilePath((String) result.get("filePath"));
        file.setPerson(person);
        fileService.persistFile(file);

        // Notification par mail
        mailService.envoyerMailNouveauDocument(destinataireEmail, request.getFileName(), lienDossier);

        // Réponse OK
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Fichier PDF téléchargé et enregistré avec succès. Une notification a été envoyée."));
    }



    @GetMapping("/download-pdf/{id}")
    public ResponseEntity<?> downloadPdf(@PathVariable Long id, @RequestParam String fileName) {
        try {
            // Recherche de l'employé ou du parent
            Employe employe = personService.findEmployeById(id);
            Parent parent = personService.findParentById(id);

            if (employe == null && parent == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, null, "Aucun parent ou employé trouvé avec l'ID fourni."));
            }

            String repositoryPath = employe != null
                    ? PathFile.REPOSITIRY_EMPLOYEE.getDescription()
                    : PathFile.REPOSITORY_PARENT.getDescription();

            Path filePath = Paths.get(repositoryPath, id.toString(), fileName);

            String base64File = FileUploadService.downloadPdf(filePath.toString());

            Map<String, String> response = new HashMap<>();
            response.put("fileBase64", base64File);
            return ResponseEntity.ok(response);

        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Fichier non trouvé."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors du traitement du fichier."));
        }
    }

    @DeleteMapping("/delete-pdf")
    public ResponseEntity<Map<String, Object>> deletePdf(
            @RequestParam Long personId,
            @RequestParam String fileName
    ) {
        // Vérifier si l'ID appartient à un employé ou un parent
        Employe employe = personService.findEmployeById(personId);
        Parent parent = personService.findParentById(personId);

        if (employe == null && parent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("success", false, "message", "Aucun parent ou employé trouvé avec l'ID fourni."));
        }

        // Déterminer le bon chemin de base selon le type de personne
        String basePath = (employe != null)
                ? PathFile.REPOSITIRY_EMPLOYEE.getDescription()
                : PathFile.REPOSITORY_PARENT.getDescription();

        // Appel du service de suppression de fichier
        Map<String, Object> result = FileUploadService.deletePdf(personId, fileName, basePath);

        if ((boolean) result.getOrDefault("success", false)) {
            fileService.deleteByName(fileName); // Suppression du fichier côté base si besoin
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }



}
