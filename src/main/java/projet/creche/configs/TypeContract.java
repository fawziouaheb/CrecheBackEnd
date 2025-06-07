package projet.creche.configs;

public enum TypeContract {
    CDD("CDD"),
    CDI("CDI"),
    STAGE("STAGE"),
    INTERSHIP("ALTERNANCE");

    private final String description;

    // Constructor
    TypeContract(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
