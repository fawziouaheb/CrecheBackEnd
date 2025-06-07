package projet.creche.dto;

public class CompteDto {

    private Long id;
    private String username;
    private String status;
    private String updatedAt;
    private String lastConnexion;
    private boolean passwordChanged;
    private RoleDto role;    // Référence par ID au lieu de la relation complète


    public CompteDto(Long id, String status, String updatedAt, String lastConnexion, boolean passwordChanged, RoleDto role) {
        this.id = id;
        this.status = status;
        this.updatedAt = updatedAt;
        this.lastConnexion = lastConnexion;
        this.passwordChanged = passwordChanged;
        this.role = role;
    }

    public CompteDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public RoleDto getRole() {
        return role;
    }
    public void setRole(RoleDto role) {
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastConnexion() {
        return lastConnexion;
    }

    public void setLastConnexion(String lastConnexion) {
        this.lastConnexion = lastConnexion;
    }

    public boolean isPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public RoleDto getRoleId() {
        return this.role;
    }

    public void setRoleId(RoleDto role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CompteDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                ", lastConnexion=" + lastConnexion +
                ", role=" + role +
                ", passwordChanged=" + passwordChanged +
                '}';
    }
}
