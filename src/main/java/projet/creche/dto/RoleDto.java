package projet.creche.dto;

import projet.creche.configs.enums.RoleType;

/**
 * Cette classe permet de filtrer les données provenant de la base de données.
 * Adaptée pour l'utilisation de RoleType comme identifiant.
 *
 * @author Fawzi Ouaheb
 */
public class RoleDto {

    private String roleName;
    private String roleDescription;
    private int accessLevel;

    public RoleDto(String roleName, String roleDescription, int accessLevel) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.accessLevel = accessLevel;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
