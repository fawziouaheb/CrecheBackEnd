package projet.creche.configs;

public enum CompteStatus {

    ENABLE("Activé"),
    DESABLED("Desactivé"),
    BLOCKED("Bloqué");

    private final String description;

    // Constructor
    CompteStatus(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
