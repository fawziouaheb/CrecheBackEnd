package projet.creche.service;

import projet.creche.configs.enums.RoleType;
import projet.creche.model.Role;

import java.util.List;

/**
 * une interface qui contient les méthodes du service
 * @author Fawzi Ouaheb
 */
public interface RoleService {
    List<Role> getAllRoles();
    Role findById(RoleType roleType);
}
