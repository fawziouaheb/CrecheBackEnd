package projet.creche.configs;

public enum ErrorMessage {

    STRUCTURE_NOT_FOUND("Structure not found with ID: "),
    INVALID_CANDIDATE("Invalid candidate data. Please verify."),
    CONFLICT_CANDIDATE_STRUCTURE("Candidate already associated with this structure.");
    private final String description;
    // Constructor
    ErrorMessage(String description) {
        this.description = description;
    }

    // Method to get the description
    public String getDescription() {
        return description;
    }
}
