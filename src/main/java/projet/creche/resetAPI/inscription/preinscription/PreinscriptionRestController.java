package projet.creche.resetAPI.inscription.preinscription;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.dto.inscription.preinscription.PreinscriptionDto;
import projet.creche.mapper.ParentMapper;
import projet.creche.mapper.inscription.preinscription.PreInscriptionMapper;
import projet.creche.model.Child;
import projet.creche.model.Compte;
import projet.creche.model.Parent;
import projet.creche.model.inscription.PreInscription;
import projet.creche.model.inscription.acteurs.Horaire;
import projet.creche.model.inscription.acteurs.ParentPreinscription;
import projet.creche.service.CompteService;
import projet.creche.service.inscription.preinscription.PreinscriptionService;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.MailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ce controller gere les pré-inscriton
 * @author Purevbaatar Amartuvshin
 */
@RestController
@RequestMapping("/preInscription/")
public class PreinscriptionRestController {
    private PreinscriptionService preinscriptionService;
    private MailService mailService;
    private CompteService compteService;
    private PreInscriptionMapper preInscriptionMapper;
    @Autowired
    public PreinscriptionRestController(PreinscriptionService preinscriptionService, MailService mailService, CompteService compteService, PreInscriptionMapper preInscriptionMapper) {
        this.preinscriptionService = preinscriptionService;
        this.mailService = mailService;
        this.compteService = compteService;
        this.preInscriptionMapper = preInscriptionMapper;
    }

    @PostMapping("/envoyer")
    public ResponseEntity<ApiResponse<Void>> envoyerFormulaire(@RequestBody PreinscriptionDto preinscriptionDto) {
        try {
            PreInscription preInscription = preInscriptionMapper.toPreinscription(preinscriptionDto);

            PreInscription saved = preinscriptionService.savePreInscription(preInscription);

            if (saved != null && saved.getId() != null) {
                mailService.envoyerMailConfirmation(saved);

                ApiResponse<Void> response = new ApiResponse<>(
                        true,
                        null,
                        "Préinscription envoyée avec succès ! Un email de confirmation a été envoyé."
                );
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Void> response = new ApiResponse<>(
                        false,
                        null,
                        "L'envoie de formulaire a échoué."
                );
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }

        } catch (Exception e) {
            ApiResponse<Void> errorResponse = new ApiResponse<>(
                    false,
                    null,
                    "Une erreur s'est produite lors de l'envoi : " + e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // Suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePreinscription(@PathVariable Long id) {
        try {
            String mail = preinscriptionService.getMailById(id);
            boolean deleted = preinscriptionService.deletePreInscriptionById(id);
            if (deleted) {
                mailService.envoyerMailRefus(mail);
                return ResponseEntity.ok(new ApiResponse<>(true, null, "Préinscription supprimée avec succès."));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(false, null, "Préinscription non trouvée."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors de la suppression : " + e.getMessage()));
        }
    }

    // Récupération de toutes les préinscriptions
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<PreinscriptionDto>>> getAllPreinscriptions() {
        try {
            List<PreInscription> all = preinscriptionService.getAll();
            List<PreinscriptionDto> dtos = all.stream()
                    .map(preInscriptionMapper::toPreinscriptionDto)
                    .toList();

            return ResponseEntity.ok(new ApiResponse<>(true, dtos, "Liste des préinscriptions récupérée avec succès."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors de la récupération : " + e.getMessage()));
        }
    }

    @GetMapping("/getPreinscriptionsParStucture")
    public ResponseEntity<ApiResponse<List<PreinscriptionDto>>> getAllPreinscriptionsByStructure(String name) {
        try {
            List<PreInscription> all = preinscriptionService.getPreinscriptionByStructureName(name);
            List<PreinscriptionDto> dtos = all.stream()
                    .map(preInscriptionMapper::toPreinscriptionDto)
                    .toList();

            return ResponseEntity.ok(new ApiResponse<>(true, dtos, "Liste des préinscriptions récupérée avec succès."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors de la récupération : " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PreinscriptionDto>> chargerPreinscription(@PathVariable Long id) {
        try {
            PreInscription preInscription = preinscriptionService.getPreInscriptionById(id);
            PreinscriptionDto dto = preInscriptionMapper.toPreinscriptionDto(preInscription);

            return ResponseEntity.ok(new ApiResponse<>(true, dto, "Préinscription récupérée avec succès."));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Préinscription non trouvée pour l'ID : " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, "Erreur lors de la récupération : " + e.getMessage()));
        }
    }

    @PostMapping("/confirm/preinscription")
    public ResponseEntity<Object> confirmPreinscription(@RequestParam Long idPreinscription) {
        if (idPreinscription == null) {
            return ResponseEntity.badRequest().body("ID de préinscription manquant");
        }

        PreInscription preInscription = preinscriptionService.getPreInscriptionById(idPreinscription);
        if (preInscription == null) {
            return ResponseEntity.notFound().build();
        }

        List<ParentPreinscription> parents = preInscription.getParents();
        if (parents == null || parents.isEmpty() || parents.get(0) == null) {
            return ResponseEntity.badRequest().body("Le premier parent est obligatoire");
        }

        Parent parent1 = ParentMapper.mapParentPreinscriptionToParent1(parents.get(0));
        parent1.setFamilySituation(preInscription.getSituationFamille().toString());
        parent1.setStructure(preInscription.getStructure());
        Child enfant = new Child();

        enfant.setFirstName(preInscription.getFirstName());
        enfant.setLastName(preInscription.getLastName());
        enfant.setDateBirth(Objects.nonNull(preInscription.getDateNaissance()) ? preInscription.getDateNaissance() : preInscription.getDateNaissancePrevue());
        enfant.setGenre(preInscription.getSexe().toString());
        enfant.setEntryDate(preInscription.getDatePrevueEntreeCreche());
        enfant.setObservation(preInscription.getInformationsComplementaires());
        enfant.setFormula(preInscription.getNombreHeuresParSemaine().toString());
        enfant.setDaysOfCare(
                preInscription.getJoursChoisis()
                        .stream()
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
        Set<Horaire> horairesCopie = preInscription.getHoraires().stream()
                .map(horaire -> {
                    Horaire copie = new Horaire(
                            horaire.getJour(),
                            horaire.getHeureArrivee(),
                            horaire.getHeureDepart()
                    );
                    copie.setChild(enfant); // 🔑 lien essentiel
                    return copie;
                })
                .collect(Collectors.toSet());

        enfant.setHoraires(horairesCopie);
        enfant.setParent(parent1);
        List<Child> childList = new ArrayList<>();
        childList.add(enfant);
        parent1.setChildren(childList);

        if (parents.size() > 1 && parents.get(1) != null) {
            Parent parent2 = ParentMapper.mapParentPreinscriptionToParent2(parent1 ,parents.get(1));
            parent2.setChildren(childList);
            parent2.setFamilySituation(preInscription.getSituationFamille().toString());
        }

        Compte compte = compteService.createCompteParent(parent1);

        //Création du compte pour le parent1
        if (Objects.nonNull(compte)) {
            mailService.envoyerMailConfirmationInscription(parent1.getEmail(), enfant.getFirstName(), parent1.getFirstName(), compte.getUsername(), compte.getPassword());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse<>(true, null, "L'inscription est acceptée. Le compte pour les parents a bien été créé !"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, null, "Une erreur est survenue lors de la création du compte parent."));

    }

}
