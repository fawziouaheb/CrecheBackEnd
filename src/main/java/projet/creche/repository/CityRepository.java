package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.City;

public interface CityRepository  extends JpaRepository<City, Long> {
    boolean existsByNameIgnoreCase(String name);


}
