package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.Role;

import java.util.Optional;

/**
 * cette interface contiendera les méthodes CRUD qui permettent de communiquer avec les base des données .
 */

public interface RoleRepository extends JpaRepository<Role, RoleType> {
    boolean existsByRoleName(String RoleName);
    Optional<Role> findByRoleName(RoleType roleName);
}
