package projet.creche.configs.enums;

public enum RoleType {
    ADMIN("Administrateur", 100),
    DIRECTOR("Directeur / Directrice", 80),
    EMPLOYEE("Employé(e)", 50),
    PARENT("Parent", 10);

    private final String description;
    private final int accessLevel;

    RoleType(String description, int accessLevel) {
        this.description = description;
        this.accessLevel = accessLevel;
    }

    public String getDescription() {
        return description;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    /**
     * Récupère un RoleType à partir d'une chaîne (ignore la casse)
     */
    public static RoleType fromString(String roleName) {
        for (RoleType role : RoleType.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Rôle inconnu : " + roleName);
    }

    @Override
    public String toString() {
        return this.name() + " (" + description + ", Niveau: " + accessLevel + ")";
    }
}
