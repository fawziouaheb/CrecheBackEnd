package projet.creche.resetAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import projet.creche.model.Menu;

import projet.creche.model.Person;
import projet.creche.service.MenuService;
import projet.creche.service.PersonService;
import projet.creche.tools.ApiResponse;


import java.io.IOException;

@RestController
@RequestMapping("/admin/menu")
public class MenuRestController {

    private final MenuService menuService;
    private final PersonService personService; // Si tu veux associer le menu à un utilisateur

    @Autowired
    public MenuRestController(MenuService menuService, PersonService personService) {
        this.menuService = menuService;
        this.personService = personService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadMenu(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "personId", required = false) Long personId
    ) {
        try {

            //  Sauvegarder l'image en base de données sous forme de BLOB
            byte[] imageBytes = file.getBytes(); // Récupérer l'image sous forme de tableau d'octets

            // Créer et sauvegarder l'objet Menu
            Menu menu = new Menu();
            menu.setTitle(title);

            // Sauvegarder l'image en BLOB dans la base de données
            menu.setImage(imageBytes); // Sauvegarder l'image sous forme de BLOB

            if (personId != null) {
                Person person = personService.findEmployeById(personId);
                if (person != null) {
                    menu.setPerson(person);
                }
            }

            menuService.persist(menu); // Sauvegarder l'entité Menu dans la base de données

            return ResponseEntity.ok(new ApiResponse(true, null, "Menu uploaded successfully!"));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, null, "Error while uploading menu: " + e.getMessage()));
        }
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllMenus() {
        try {
            var menus = menuService.findAll(); // méthode dans ton service
            return ResponseEntity.ok(new ApiResponse(true, menus, "Menus loaded successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, null, "Failed to load menus: " + e.getMessage()));
        }
    }
    @GetMapping("/last")
    public ResponseEntity<ApiResponse> getLastMenu() {
        try {
            Menu lastMenu = menuService.findLastMenu();
            System.out.println("lastmenu "+lastMenu);
            return ResponseEntity.ok(new ApiResponse(true, lastMenu, "Last menu loaded successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, null, "Failed to load last menu: " + e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMenu(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = menuService.deleteMenu(id);
            if (isDeleted) {
                return ResponseEntity.ok(new ApiResponse(true, null, "Menu deleted successfully!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, null, "Menu not found!"));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, null, "Failed to delete menu: " + e.getMessage()));
        }
    }

}