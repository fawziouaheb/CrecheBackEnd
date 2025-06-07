package projet.creche.resetAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.configs.Links;
import projet.creche.dto.RapportDto;
import projet.creche.mapper.RapportMapper;
import projet.creche.model.Compte;
import projet.creche.model.Employe;
import projet.creche.model.Rapport;
import projet.creche.model.Structure;
import projet.creche.service.CompteService;
import projet.creche.service.PersonService;
import projet.creche.service.RapportService;
import projet.creche.service.StructureService;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.MailService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/Admin/Rapport")
public class RapportRestController {

    private RapportService rapportService;
    private StructureService structureService;
    private PersonService personService;
    private MailService mailService;
    private CompteService compteService;
    @Autowired
    public RapportRestController(RapportService rapportService, StructureService structureService,PersonService personService, MailService mailService, CompteService compteService) {
        this.rapportService = rapportService;
        this.structureService = structureService;
        this.personService = personService;
        this.mailService = mailService;
        this.compteService = compteService;
    }

    @GetMapping("/get-by-date-and-structure")
    public ResponseEntity<ApiResponse> getRapportByDateAndStructure(
            @RequestParam("structureId") Long structureId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        // Conversion LocalDate → java.sql.Date
        Date sqlDate = Date.valueOf(date);

        // Recherche du rapport
        Optional<Rapport> rapportOpt = rapportService.findByStructureIdAndDate(structureId, sqlDate);

        // Vérification si le rapport existe
        if (rapportOpt.isPresent()) {
            // On récupère l'objet Rapport à l'intérieur de l'Optional
            Rapport rapport = rapportOpt.get();
            RapportDto rapportDto = RapportMapper.mappeEntityToDto(rapport);
            return ResponseEntity.ok(new ApiResponse<>(true, rapportDto, "Rapport trouvé"));
        } else {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Aucun rapport trouvé pour cette structure et cette date"));
        }
    }

    @GetMapping("/get-by-date-and-user")
    public ResponseEntity<ApiResponse> getRapportByDateAndUser(
            @RequestParam("userId") Long userId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        // Conversion LocalDate → java.sql.Date
        Date sqlDate = Date.valueOf(date);
        Compte compte = this.compteService.findById(userId);
        Employe employe = (Employe)compte.getPerson();
        // Recherche du rapport par userId et date
        Optional<Rapport> rapportOpt = rapportService.findByStructureIdAndDate(employe.getStructure().getId(), sqlDate);

        // Vérification si le rapport existe
        if (rapportOpt.isPresent()) {
            Rapport rapport = rapportOpt.get();
            RapportDto rapportDto = RapportMapper.mappeEntityToDto(rapport);
            return ResponseEntity.ok(new ApiResponse<>(true, rapportDto, "Rapport trouvé"));
        } else {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Aucun rapport trouvé pour cet utilisateur et cette date"));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody RapportDto rapportDto) {
        Structure structure = structureService.findById(rapportDto.getStructureId());
        if (structure == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Structure non trouvée avec l'ID : " + rapportDto.getStructureId()));
        }

        Optional<Rapport> existingRapport = rapportService.findByStructureIdAndDate(structure.getId(), rapportDto.getRapportDate());

        String sujetReunion = "Compte rendu - Réunion du " + rapportDto.getRapportDate();
        String lienRapport = Links.LINKREUNION.getDescription(); // à ajuster si besoin

        if (existingRapport.isPresent()) {
            // Mise à jour
            Rapport rapport = existingRapport.get();
            rapport.setBodyRapport(rapportDto.getRapportBody());
            rapportService.saveRapport(rapport);

            // Envoi des mails
            structure.getPerson().stream()
                    .filter(person -> person instanceof Employe)
                    .filter(person -> person.getCompte() != null && person.getCompte().getUsername() != null)
                    .forEach(person -> {
                        String email = person.getCompte().getUsername();
                        mailService.envoyerMailCompteRendu(email, sujetReunion, rapportDto.getRapportBody(), lienRapport);
                    });

            return ResponseEntity.ok(new ApiResponse(true, rapportDto, "Le rapport a été mis à jour avec succès. Les notifications ont été envoyées."));
        } else {
            // Nouveau rapport
            Rapport newRapport = RapportMapper.mappeDtoToEntity(rapportDto);
            newRapport.setStructure(structure);
            rapportService.saveRapport(newRapport);

            // Envoi des mails
            structure.getPerson().stream()
                    .filter(person -> person instanceof Employe)
                    .filter(person -> person.getCompte() != null && person.getCompte().getUsername() != null)
                    .forEach(person -> {
                        String email = person.getCompte().getUsername();
                        mailService.envoyerMailCompteRendu(email, sujetReunion, rapportDto.getRapportBody(), lienRapport);
                    });

            return new ResponseEntity<>(new ApiResponse(true, rapportDto, "Le rapport a été bien enregistré. Les notifications ont été envoyées."), HttpStatus.CREATED);
        }
    }

    @PostMapping("/views")
    public ResponseEntity<ApiResponse> asViews(
            @RequestParam("idRapport") Long idRapport,
            @RequestParam("idUser") Long idUser) {

        Rapport rapport = this.rapportService.getRapport(idRapport);
        if (rapport == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Rapport non trouvé avec l'ID : " + idRapport));
        }

        Employe employe = this.personService.findEmployeById(idUser);
        if (employe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, "Employé non trouvé avec l'ID : " + idUser));
        }

        // Ajout de l'employé à la liste des vues si non déjà présent
        if (!rapport.getEmployes().contains(employe)) {
            rapport.getEmployes().add(employe);
            this.rapportService.saveRapport(rapport); // Sauvegarde de la mise à jour
        }

        return ResponseEntity.ok(new ApiResponse<>(true, RapportMapper.mappeEntityToDto(rapport), "Rapport marqué comme vu par l'utilisateur."));
    }

}
