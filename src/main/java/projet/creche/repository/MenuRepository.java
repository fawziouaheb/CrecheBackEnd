package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findTopByOrderByIdDesc();
}
