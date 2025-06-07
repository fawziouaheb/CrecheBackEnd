package projet.creche.resetAPI;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.creche.mapper.RapportMapper;
import projet.creche.model.City;
import projet.creche.service.CityService;
import projet.creche.tools.ApiResponse;

import java.util.List;

/**
 * Ce contrôleur contient les routes pour communiquer avec le backend pour gérer les villes.
 */
@RestController
@RequestMapping("/administrator/city")
public class CityRestController {

    private final CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCity(@RequestBody City city) {
        if (cityService.cityExistsByName(city.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)  // 409 = Conflit
                    .body(new ApiResponse<>(false, null, "Une ville avec ce nom existe déjà."));
        }

        City savedCity = this.cityService.saveCity(city);
        return new ResponseEntity<>(
                new ApiResponse<>(true, savedCity, "La ville a été ajoutée avec succès."),
                HttpStatus.CREATED
        );
    }


    /**
     * Mettre à jours une villes
     * @param updatedCityDetails les paramteres à mettre à jours
     * @return la ville
     */
    @PutMapping("/update/")
    public ResponseEntity<ApiResponse> updateCity(@RequestBody City updatedCityDetails) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, this.cityService.saveCity(updatedCityDetails), "La ville a été mise à jour avec succès.")
        );
    }



    /**
     * Récupérer les informations d'une ville par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(
            @Parameter(description = "ID de la ville à récupérer", required = true)
            @PathVariable("id") Long id
    ) {
        City city = this.cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    /**
     * Récupérer toutes les villes.
     */
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllCities() {
        List<City> cities = this.cityService.getCities();

        if (cities.isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, cities, "Aucune ville n'a été sauvegardée dans le système.")
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, cities, "La liste des villes a été récupérée avec succès.")
        );
    }


    /**
     * Supprimer une ville par son ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(
            @Parameter(description = "ID de la ville à supprimer", required = true)
            @PathVariable("id") Long id
    ) {
        City city = this.cityService.findById(id);
        this.cityService.deleteCity(city);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
