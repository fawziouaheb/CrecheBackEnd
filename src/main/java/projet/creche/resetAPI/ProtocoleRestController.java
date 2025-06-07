package projet.creche.resetAPI;


import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.dto.ProtocoleDto;
import projet.creche.model.Protocole;
import projet.creche.service.ProtocoleService;

import java.util.List;

@RestController
@RequestMapping("/protocoles")
public class ProtocoleRestController {

    private final ProtocoleService protocoleService;

    // Injection de dépendance
    @Autowired
    public ProtocoleRestController(ProtocoleService protocoleService) {
        this.protocoleService = protocoleService;
    }

    // Endpoint pour récupérer tous les protocoles
    @GetMapping
    public ResponseEntity<List<Protocole>> getAllProtocoles() {
        List<Protocole> protocoles = protocoleService.getAllProtocoles();
        return new ResponseEntity<>(protocoles, HttpStatus.OK);
    }

    // Endpoint pour récupérer un protocole par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Protocole> getProtocoleById(
            @Parameter(description = "ID du protocole à charger", required = true)
            @PathVariable("id") Long id) {
        Protocole protocole = protocoleService.findById(id);
        if (protocole != null) {
            return new ResponseEntity<>(protocole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour récupérer un protocole par son titre
    @GetMapping("/search")
    public ResponseEntity<Protocole> getProtocoleByTitre(@RequestParam String titre) {
        Protocole protocole = protocoleService.findByName(titre);
        if (protocole != null) {
            return new ResponseEntity<>(protocole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour ajouter un nouveau protocole
    @PostMapping
    public ResponseEntity<Protocole> addProtocole(@RequestBody ProtocoleDto protocoleDto) {
        // Utilisation du constructeur avec paramètres
        Protocole protocole = new Protocole(protocoleDto.getTitre(), protocoleDto.getContenu());

        Protocole createdProtocole = protocoleService.addProtocole(protocole);
        return new ResponseEntity<>(createdProtocole, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un protocole
    @PutMapping("/{id}")
    public ResponseEntity<Protocole> updateProtocole(
            @Parameter(description = "ID du protocole à modifier", required = true)
            @PathVariable("id") Long id,
            @RequestBody ProtocoleDto protocoleDto) {
        Protocole updatedProtocole = protocoleService.updateProtocole(id, new Protocole(protocoleDto.getTitre(), protocoleDto.getContenu()));

        if (updatedProtocole != null) {
            return new ResponseEntity<>(updatedProtocole, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un protocole
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProtocole(
            @Parameter(description = "ID du protocole à supprimer", required = true)
            @PathVariable("id") Long id) {
        boolean isDeleted = protocoleService.deleteProtocole(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
