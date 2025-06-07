package projet.creche.configs;

public enum ApplicationStatus {
    IN_PROGRESS("En cours"),
    ACCEPTED("Accepetée"),
    REJECTED("Rejetée");

    private final String description;

    // Constructor
    ApplicationStatus(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
