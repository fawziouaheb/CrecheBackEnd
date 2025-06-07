package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.configs.enums.RoleType;
import projet.creche.model.Role;
import projet.creche.repository.RoleRepository;
import projet.creche.service.RoleService;

import java.util.List;

/**
 * Implémentation du service Role pour la gestion des rôles fixes.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retourne la liste de tous les rôles définis.
     */
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    /**
     * Recherche un rôle par son RoleType.
     */
    @Override
    public Role findById(RoleType roleType) {
        return roleRepository.findById(roleType)
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé : " + roleType));
    }
}
