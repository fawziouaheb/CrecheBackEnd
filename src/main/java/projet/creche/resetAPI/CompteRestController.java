package projet.creche.resetAPI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.model.Compte;
import projet.creche.repository.CompteRepository;
import projet.creche.repository.RoleRepository;
import projet.creche.repository.PersonRepository;
import projet.creche.service.CompteService;

@RestController
@RequestMapping("/api/comptes")
@Tag(name = "Comptes", description = "Gestion des comptes utilisateurs")
public class CompteRestController {

    private final CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/generate")
    @Operation(summary = "Générer un compte aléatoire", description = "Crée un compte utilisateur avec des données aléatoires et l'enregistre en base.")
    public ResponseEntity<Compte> generateRandomCompte() {
        Compte compte = compteService.generateCompte();

        Compte savedCompte = compteService.save(compte);

        return ResponseEntity.ok(savedCompte);
    }
}
