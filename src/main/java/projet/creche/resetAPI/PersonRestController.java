package projet.creche.resetAPI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.configs.CompteStatus;
import projet.creche.configs.enums.RoleType;
import projet.creche.dto.EmployeDto;
import projet.creche.dto.parent.ChildDto;
import projet.creche.dto.parent.ParentDto;
import projet.creche.mapper.EmployeMapper;
import projet.creche.mapper.ParentMapper;
import projet.creche.model.*;
import projet.creche.service.*;
import projet.creche.tools.ApiResponse;
import projet.creche.tools.Generate;
import org.springframework.security.crypto.password.PasswordEncoder;
import projet.creche.tools.MailService;

import java.util.*;

/**
 * cette class contient les routes pour le backend
 */
@RestController
@RequestMapping("/Director/person")
public class PersonRestController {
    private static final String DIRECTORY = "uploads/";
    private PersonService personService;
    private StructureService structureService;
    private CompteService compteService;
    private RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private MailService mailService;

    @Autowired
    public PersonRestController(PersonService personService, StructureService structureService, CompteService compteService, RoleService roleService, PasswordEncoder passwordEncoder, MailService mailService) {
        this.personService = personService;
        this.structureService = structureService;
        this.compteService = compteService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    /**
     * récuperer un employer par id
     */
    @GetMapping("/getEmploye/{id}")
    public ResponseEntity<ApiResponse> getEmploye(@PathVariable("id") Long employeId){
        Employe employe = this.personService.findEmployeById(employeId);
        if(employe == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "L'employé avec l'id : "+employeId+" n'existe pas." ));
        }
        EmployeDto employeDto =  EmployeMapper.mappeEntityToDto(employe);
        employeDto.setStructureName(employe.getStructure().getStructureName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, employeDto, "L'employé a été récupéré avec succès." ));
    }


    @GetMapping("/getAll/employe")
    public ResponseEntity<List<EmployeDto>> getAll() {
        return new ResponseEntity<List<EmployeDto>>(EmployeMapper.mappeListEntityToListDtos(this.personService.getAllEmploye()),HttpStatus.OK);
    }
    @GetMapping("/getAll/employe/byStructure")
    public ResponseEntity<List<EmployeDto>> getAllByStructure(@RequestParam String structureName) {
        List<EmployeDto> employes = EmployeMapper.mappeListEntityToListDtos(
                this.personService.getAllEmployeByStructure(structureName)
        );
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    @GetMapping("/getAll/parent/byStructure")
    public ResponseEntity<List<ParentDto>> getAllParentsByStructure(@RequestParam String structureName) {
        List<ParentDto> parents = ParentMapper.mapEntitiesToDtos(
                personService.getAllParentsByStructure(structureName)
        );
        return new ResponseEntity<>(parents, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> updateEmploye(@RequestBody  EmployeDto employeDto){

        // Validation des données de l'employé
        if (!EmployeDto.isValid(employeDto)) {
            // Debugging: Log des données invalides
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Données de l'employé invalides. Veuillez vérifier."));
        }

        // Recherche de la structure
        Structure structure = structureService.findById(employeDto.getStructureId());
        if (structure == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Structure non trouvée avec l'ID : " + employeDto.getStructureId()));
        }
        employeDto.setStructure(structure);

        // Vérifier si l'employé existe déjà
        Employe employe = this.personService.findEmployeByEMail(employeDto.getEmail().trim().toUpperCase());
        if (employe == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Le compte n'exist pas: " + employeDto.getEmail()));
        }
        employe.setFirstName(employeDto.getFirstName());
        employe.setLastName(employeDto.getLastName());
        employe.setDateBirth(Generate.StringToDate(employeDto.getDateBirth()));
        employe.setTypeContract(employeDto.getTypeContratct());
        employe.setDateBeginContract(Generate.StringToDate(employeDto.getDateBeginContract()));
        employe.setDateEndContract(Generate.StringToDate(employeDto.getDateEndContract()));
        employe.setMobile(employeDto.getMobile());
        employe.setStructure(structure);
        this.personService.persistEmploye(employe);  // Persister l'employé avant de l'utiliser

        Compte compte = this.compteService.findById(employeDto.getcompteId());
        Role role = this.roleService.findById(RoleType.valueOf(employeDto.getCompte().getRole().getRoleName().name()));

        compte.setRole(role);
        compte.setUsername(employe.getEmail());

        //Persister le compte
        compteService.save(compte);
        // Réponse réussie
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, null, "L'employé a été modifié avec succès."));
    }

    @PostMapping("/add/employe")
    public ResponseEntity<Object> add(@RequestBody EmployeDto employeDto) {
        // Vérifier si l'employé existe déjà
        Employe employe = this.personService.findEmployeByEMail(employeDto.getEmail().trim().toUpperCase());
        if (employe != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(false, null, "L'email existe déjà : " + employeDto.getEmail()));
        }

        // Validation des données de l'employé
        if (!EmployeDto.isValid(employeDto)) {
            // Debugging: Log des données invalides
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(false, null, "Données de l'employé invalides. Veuillez vérifier."));
        }

        // Recherche de la structure
        Structure structure = structureService.findById(employeDto.getStructureId());
        if (structure == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Structure non trouvée avec l'ID : " + employeDto.getStructureId()));
        }
        employeDto.setStructure(structure);

        // Sauvegarde du repository


        employe = EmployeMapper.mappeDtoToEntity(employeDto);
        this.personService.persistEmploye(employe);  // Persister l'employé avant de l'utiliser

        // Créer et persister le compte
        Compte compte = new Compte();
        compte.setStatus(CompteStatus.ENABLE.getDescription());
        String passWord = Generate.password();
        compte.setPassword(passwordEncoder.encode(passWord));
        Role role = this.roleService.findById(RoleType.EMPLOYEE);

        compte.setRole(role);
        compte.setPerson(employe);
        compte.setUsername(employe.getEmail());

        //Persister le compte
        compteService.save(compte);

        // 📧 Envoi de l'email avec les identifiants
        this.mailService.envoyerMailCreationEmploye(employe.getEmail(), passWord);

        // Réponse réussie
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, null, "L'employé a été ajouté avec succès. Un mail de confirmation a été envoyé à  \n" + employe.getEmail()));
    }



    @PostMapping("/add/parent")
    public ResponseEntity<Object> addParent(@RequestBody ParentDto parentDto) {

            // Vérifier si l'email du premier parent existe déjà
            Parent existingParent = this.personService.findParentByEmail(parentDto.getEmail());
            if (existingParent != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(false, null, "L'email du premier parent existe déjà."));
            }

            // Vérifier si l'email du deuxième parent existe déjà (s'il est fourni)
            if (parentDto.getSecondParentEmail() != null && !parentDto.getSecondParentEmail().isEmpty()) {
                Parent existingSecondParent = this.personService.findParentByEmail(parentDto.getSecondParentEmail());
                if (existingSecondParent!= null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ApiResponse<>(false, null, "L'email du deuxième parent existe déjà."));
                }
            }

            // Créer un nouvel objet Parent
            Parent parent = new Parent();
            parent.setFirstName(parentDto.getFirstName());
            parent.setLastName(parentDto.getLastName());
            parent.setEmail(parentDto.getEmail());
            parent.setDateBirth(Generate.StringToDate(parentDto.getDateBirth()));
            parent.setMobile(parentDto.getMobile());
            parent.setAddress_parent(parentDto.getAddress());// Ajout de l'adresse du parent
            parent.setProfession(parentDto.getProfession());
            parent.setFamilySituation(parentDto.getFamilySituation());

            // Ajouter le deuxième parent s'il existe
            parent.setSecondParentFirstName(parentDto.getSecondParentFirstName());
            parent.setSecondParentLastName(parentDto.getSecondParentLastName());
            parent.setSecondParentEmail(parentDto.getSecondParentEmail());
            parent.setSecondParentMobile(parentDto.getSecondParentMobile());
            parent.setSecondParentProfession(parentDto.getSecondParentProfession());
            parent.setSecondParentFamilySituation(parentDto.getSecondParentFamilySituation());


            // Associer la structure et le rôle
            Structure structure = this.structureService.findById(parentDto.getStructureId());
            parent.setStructure(structure);

            //créer un dossier au parent



            // Sauvegarder le parent
            Parent savedParent = this.personService.persistParent(parent);

            // Ajouter les enfants
            List<Child> children = new ArrayList<>();
            for (ChildDto childDto : parentDto.getChildren()) {
                Child child = new Child();
                child.setLastName(childDto.getLastName());
                child.setFirstName(childDto.getFirstName());
                child.setDaysOfCare(childDto.getDaysOfCare());
                child.setFormula(childDto.getFormula());
                child.setDateBirth(Generate.StringToDate(childDto.getDate_birth_child()));
                child.setGenre(childDto.getGenre());
                child.setObservation(childDto.getObservation());
                child.setEntryDate(Generate.StringToDate(childDto.getEntryDate()));
                // Associer l'enfant au parent
                child.setParent(savedParent);
                children.add(child);
            }

            // Sauvegarder les enfants
            this.personService.saveAll(children);

            // Créer et persister le compte
            Compte compte = new Compte();
            compte.setStatus(CompteStatus.ENABLE.getDescription());
            String password = Generate.password();
            compte.setPassword(passwordEncoder.encode(password));
            Role role = this.roleService.findById(RoleType.PARENT);

            compte.setRole(role);
            compte.setPerson(parent);
            compte.setUsername(parent.getEmail());

            //Persister le compte
            compteService.save(compte);


            //Retourner une réponse de succès
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(true, savedParent.getId(), "Le parent a été ajouté avec succès. Voici les identifiants  de connexion l'adresse mail : "+compte.getUsername()+ " le mot de passe : " + password));

    }

    @GetMapping("/getAll/parents")
    public ResponseEntity<ApiResponse> getAllParents() {
        // Correction du type de retour
        List<Parent> parents = this.personService.findAllParents();

        if (!parents.isEmpty()) {
            List<ParentDto> parentDtos = ParentMapper.mapEntitiesToDtos(parents);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(true, parentDtos, "Les parents ont été bien récupéré."));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, null, "Il y'a eu une erreur de récupération des parents"));
    }

    /**
     * récuperer un employer par id
     */
    @GetMapping("/getParent/{id}")
    public ResponseEntity<ApiResponse> getParent(@PathVariable("id") Long parentId){
        Parent parent = this.personService.findParentById(parentId);
        if(parent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, null, "Le parent avec l'id : "+parentId+" n'existe pas." ));
        }
        ParentDto  parentDto = ParentMapper.mapEntityToDto(parent);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, parentDto, "Le parent a été récupéré avec succès." ));
    }

}
