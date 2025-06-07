package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.classTool.SessionSearchRequest;
import projet.creche.dto.WorkingSessionDto;
import projet.creche.mapper.WorkingSessionMapper;
import projet.creche.model.Compte;
import projet.creche.model.Employe;
import projet.creche.model.WorkingSession;
import projet.creche.service.CompteService;
import projet.creche.service.PersonService;
import projet.creche.service.WorkingSessionService;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.Generate;

import java.util.List;

@RestController
@RequestMapping("/administrator/working-session")
public class WorkingSessionController {

    private WorkingSessionService workingSessionService;
    private PersonService personService;
    private CompteService compteService;
    @Autowired
    public WorkingSessionController(
            WorkingSessionService workingSessionService,
            PersonService personService,
            CompteService compteService) {
        this.workingSessionService = workingSessionService;
        this.personService = personService;
        this.compteService = compteService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody WorkingSessionDto workingSessionDto) {
        Compte compte = this.compteService.findById(workingSessionDto.getEmployeId());
        Employe employe = (Employe)compte.getPerson();
        if (employe == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Aucun employé trouvé avec l'ID fourni."));
        }
        workingSessionDto.setEmploye(employe);
        this.workingSessionService.save(WorkingSessionMapper.mappeDtoToEntity(workingSessionDto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, null,"Arrivée à " + workingSessionDto.getArrivalTime().toString()+ " Départ à " + workingSessionDto.getDepartureTime().toString()+ " enregistré avec succès !"));
    }


    @PostMapping("/sessions-by-month")
    public ResponseEntity<ApiResponse> getWorkingSessionsByMonth(@RequestBody SessionSearchRequest request) {
        Employe employe = this.personService.findEmployeById(request.getEmployeId());
        if (employe == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Aucun employé trouvé avec l'ID fourni."));
        }

        List<WorkingSession> sessions = this.workingSessionService.findSessionsBetweenDates(request.getEmployeId(), Generate.StringToDate(request.getDateDebut()), Generate.StringToDate(request.getDateFin()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, sessions, "Sessions récupérées avec succès"));
    }


    @GetMapping("/All/{employeId}")
    public ResponseEntity<ApiResponse> getSessionsByEmploye(@PathVariable Long employeId) {
        Compte compte = this.compteService.findById(employeId);
        Employe employe = (Employe)compte.getPerson();
        List<WorkingSessionDto> sessions = WorkingSessionMapper.mappeEntitiesToDtos(workingSessionService.getAllSessionsByEmploye(employe.getId()));
                return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, sessions, "Sessions  récupérées avec succès"));
    }
}
