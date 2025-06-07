package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.configs.ApplicationStatus;
import projet.creche.configs.ErrorMessage;
import projet.creche.configs.PathFile;
import projet.creche.dto.CandidateDto;
import projet.creche.mapper.CandidateMapper;
import projet.creche.model.Candidate;
import projet.creche.model.Structure;
import projet.creche.service.CandidateService;
import projet.creche.service.StructureService;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.FileUploadService;
import projet.creche.tools.Generate;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import projet.creche.tools.MailService;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ce controller contient les routes pour communiquer avec le backend
 */
@RestController
@RequestMapping("/candidacy/")
public class CandidateRestController {

    private CandidateService candidateService;
    private StructureService structureService;
    private MailService mailService;

    @Autowired
    public CandidateRestController(CandidateService candidateService, StructureService structureService, MailService mailService) {
        this.structureService = structureService;
        this.candidateService = candidateService;
        this.mailService = mailService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestBody CandidateDto candidateDto) {

        candidateDto.setCV(Generate.saveToFile(candidateDto.getCV(), candidateDto.getLastName() + "-" + candidateDto.getFirstName() + ".pdf", PathFile.CV.getDescription()));

        Structure structure = structureService.findById(candidateDto.getStructureId());
        if (structure == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false,null,"Structure non trouvée."));
        }

        Candidate existingCandidate = candidateService.findByEmail(candidateDto.getEmail().trim().toUpperCase());
        Candidate candidate;

        if (existingCandidate != null) {
            candidate = existingCandidate;
        } else {
            // Mapper et valider un nouveau candidat si inexistant
            candidate = CandidateMapper.mappeDtoToEntity(candidateDto);
            if (!candidateService.isValidAndFilter(candidate)) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse(false,null,"Les données saisies sont invalides."));
            }
        }

        if (candidate.getStructures().contains(structure)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse(false,null,"Vous avez déjà postulé sur notre site."));
        }

        structure.getCandidates().add(candidate);
        candidate.getStructures().add(structure);

        candidateService.addCandidate(candidate);
        CandidateDto createdCandidateDto = CandidateMapper.mappeEntityToDto(candidate);
        //Confirmation par mail
        mailService.envoyerMailCandidatureReçue(
                candidate.getEmail(),
                candidate.getFirstName(),
                candidate.getLastName(),
                candidate.getContract(),
                structure.getStructureName()
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, createdCandidateDto, "Votre candidature a été bien transmise."));
    }

    /**
     * Route pour accepter une candidature
     * @param candidateId l'ID du candidat
     * @return ResponseEntity avec le statut de la mise à jour
     */
    @PatchMapping(value = "/accept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> acceptCandidate(@PathVariable("id") Long candidateId) {
        Candidate candidate = candidateService.findById(candidateId);
        if (candidate == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(false, candidate, "Candidat n'a pas été trouvé"));
        }

        candidate.setStatut(ApplicationStatus.ACCEPTED.getDescription());
        candidateService.addCandidate(candidate);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, candidate.getFirstName(), "Le candidate  "+candidate.getFirstName() + " "+candidate.getLastName()  + "  a été accepté(e)."));
    }


    /**
     * Route pour rejeter un candidat.
     * @param candidateId l'ID du candidat
     * @return ResponseEntity avec le statut de la mise à jour
     */
    @PostMapping(value = "/rejete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> rejectCandidat(@PathVariable("id") Long candidateId) {

        Candidate candidate = candidateService.findById(candidateId);
        if (candidate == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(false, null, "Le candidat n'a pas été trouvé."));
        }

        // Suppression du fichier CV
        String cvPath = candidate.getcv();
        if (cvPath != null && !cvPath.isEmpty()) {
            File cvFile = new File(cvPath);
            if (cvFile.exists() && !cvFile.delete()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse<>(false, null, "Le fichier CV n'a pas pu être supprimé."));
            }
        }

        // Détacher les relations ManyToMany
        for (Structure structure : candidate.getStructures()) {
            structure.getCandidates().remove(candidate);
        }
        candidate.getStructures().clear();

        // Mise à jour du statut
        candidate.setStatut(ApplicationStatus.REJECTED.getDescription());

        // Suppression finale
        candidateService.delete(candidate);
        //Confirmation par mail
        mailService.envoyerMailCandidatureRefusee(candidate.getEmail(),candidate.getFirstName(),candidate.getLastName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, candidate.getFirstName(),
                        "Le candidat " + candidate.getFirstName() + " " + candidate.getLastName() + " a été rejeté."));
    }



    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(
                true,
                CandidateMapper.mappeListEntityToListDtos(this.candidateService.getAll()),
                "La liste des candidats a été chargée avec succès."
        ),HttpStatus.OK);
    }

    @GetMapping("/downloadCV")
    public ResponseEntity<?> downloadCV(@RequestParam String filePath) {
        try {
            // Téléchargement du fichier PDF à partir du chemin fourni
            String base64File = FileUploadService.downloadPdf(filePath);

            // Création d'une réponse JSON simple
            Map<String, String> response = new HashMap<>();
            response.put("fileBase64", base64File);

            return ResponseEntity.ok(response);

        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Fichier non trouvé au chemin spécifié."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors du téléchargement du CV."));
        }
    }



}
