package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByKey(String key);
    List<MenuItem> findByVisibleTrueAndRolesContaining(RoleType role);
}
